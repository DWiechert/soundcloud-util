package com.github.dwiechert.sc.util.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.github.dwiechert.sc.util.Constants;
import com.github.dwiechert.sc.util.models.FolderConfig;
import com.github.dwiechert.sc.util.models.SyncConfig;
import com.soundcloud.api.Request;

public class SyncCheckCommand extends AbstractSyncCommand {
	@Override
	public String getName() {
		return "syncCheck";
	}

	@Override
	public String getDescription() {
		return "Checks to see what songs you don't have synced from SoundCloud per artist in the scsync.config file.";
	}

	@Override
	public Options getOptions() {
		return super.getOptions();
	}

	@Override
	public void run(final String... args) {
		final CommandLine line = parseArguments(args);
		final String configFile = getConfigFile(line);
		final SyncConfig config = readConfig(configFile);

		for (final FolderConfig folderConfig : config.getConfigs()) {
			String artistString = null;
			String user = null;
			try {
				final long artistId = api.resolve(folderConfig.getArtistUrl());
				final HttpResponse artistResponse = api.get(new Request(String.format(Constants.ARTIST_TRACK_URL, artistId, Constants.CLIENT_ID)));
				artistString = EntityUtils.toString(artistResponse.getEntity());
				
				final HttpResponse userResponse = api.get(new Request(String.format(Constants.ARTIST_URL, artistId, Constants.CLIENT_ID)));
				user = EntityUtils.toString(userResponse.getEntity());
			} catch (final Exception e) {
				e.printStackTrace();
			}

			final JSONObject userObj = new JSONObject(user);
			System.out.println("Songs that need to sync from user: " + userObj.getString("username"));
			final JSONArray array = new JSONArray(artistString);
			for (int i = 0; i < array.length(); i++) {
				final JSONObject obj = array.getJSONObject(i);
				System.out.println(obj);
			}
		}
	}
}
