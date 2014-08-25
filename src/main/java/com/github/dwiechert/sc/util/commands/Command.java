package com.github.dwiechert.sc.util.commands;

import java.io.IOException;

import org.apache.commons.cli.Options;

import com.github.dwiechert.sc.util.Constants;
import com.soundcloud.api.ApiWrapper;
import com.soundcloud.api.CloudAPI;
import com.soundcloud.api.Token;

public abstract class Command {
	public abstract String getName();

	public abstract String getDescription();

	public abstract void run(String... args);

	public abstract Options getOptions();

	protected final CloudAPI api;

	public Command() {
		try {
			this.api = cloudApi();
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
}
