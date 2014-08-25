package com.github.dwiechert.sc.util.commands;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.cli.Options;

import com.github.dwiechert.sc.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.soundcloud.api.ApiWrapper;
import com.soundcloud.api.CloudAPI;
import com.soundcloud.api.Token;

public abstract class Command {
	public abstract String getName();

	public abstract String getDescription();

	public abstract void run(String... args);

	public abstract Options getOptions();

	protected final CloudAPI api;

	protected final Gson gson;

	public Command() {
		try {
			this.api = cloudApi();
			this.gson = gsonCloudApi();
		} catch (IOException e) {
			throw new RuntimeException("Error initializing API.", e);
		}
	}

	/**
	 * Creates a {@link CloudAPI} bean.
	 * 
	 * @return A {@link CloudAPI} bean.
	 * @throws IOException
	 *             If an error occurs.
	 */
	private CloudAPI cloudApi() throws IOException {
		final CloudAPI api = new ApiWrapper(Constants.CLIENT_ID, Constants.CLIENT_SECRET, null, null);
		final Token token = api.login(Constants.SOUNDCLOUD_USERNAME, Constants.SOUNDCLOUD_PASSWORD);
		api.setToken(token);
		return api;
	}

	/**
	 * Creates a {@link Gson} bean. The difference of this bean vs possibly other {@link Gson} beans is that this one has overridden the parsing of
	 * dates to coincide with the way dates are stored within SoundCloud.
	 * 
	 * @return A {@link Gson} bean.
	 */
	private Gson gsonCloudApi() {
		final GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			// Year in 4, month in 2, day in 2, hour in 24, minutes in hour, seconds in minute, timezone in 4
			final DateFormat df = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss Z");

			@Override
			public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
				try {
					return df.parse(json.getAsString());
				} catch (final java.text.ParseException e) {
					e.printStackTrace();
					return null;
				}
			}
		});
		return builder.create();
	}
}
