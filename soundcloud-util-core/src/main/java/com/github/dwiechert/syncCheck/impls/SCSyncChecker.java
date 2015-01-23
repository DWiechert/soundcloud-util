package com.github.dwiechert.syncCheck.impls;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.github.dwiechert.sc.util.Constants;
import com.github.dwiechert.sc.util.models.FolderConfig;
import com.github.dwiechert.syncCheck.SyncChecker;
import com.soundcloud.api.ApiWrapper;
import com.soundcloud.api.CloudAPI;
import com.soundcloud.api.Request;

public class SCSyncChecker implements SyncChecker {
	private CloudAPI api = new ApiWrapper(Constants.CLIENT_ID, Constants.CLIENT_SECRET, null, null);
	private int apiCount = 0;

	@Override
	public void check(final FolderConfig folderConfig) {
		String artistString = null;
		String user = null;
		try {
			final long artistId = getApi().resolve(folderConfig.getArtistUrl());
			final HttpResponse artistResponse = getApi().get(new Request(String.format(Constants.ARTIST_TRACK_URL, artistId, Constants.CLIENT_ID)));
			artistString = EntityUtils.toString(artistResponse.getEntity());

			final HttpResponse userResponse = getApi().get(new Request(String.format(Constants.ARTIST_URL, artistId, Constants.CLIENT_ID)));
			user = EntityUtils.toString(userResponse.getEntity());
		} catch (final Exception e) {
			e.printStackTrace();
		}

		final JSONObject userObj = new JSONObject(user);
		System.out.println("Songs that need to sync from user: " + userObj.getString("username"));
		final JSONArray array = new JSONArray(artistString);
		for (int i = 0; i < array.length(); i++) {
			final JSONObject obj = array.getJSONObject(i);
			checkSong(obj.getString("permalink_url"), folderConfig.getLocalFolder());
		}
	}

	private void checkSong(final String song, final String localFolder) {
		String trackString = null;
		try {
			final long trackId = getApi().resolve(song);
			final HttpResponse trackResponse = getApi().get(new Request(String.format(Constants.TRACK_URL, trackId, Constants.CLIENT_ID)));
			trackString = EntityUtils.toString(trackResponse.getEntity());
		} catch (final Exception e) {
			e.printStackTrace();
		}

		final JSONObject obj = new JSONObject(trackString);
		final boolean streamable = obj.getBoolean("streamable");

		if (streamable) {
			// Replace all illegal characters with 'space hyphen'
			final String title = obj.getString("title").replace(":", " -").replace("\\", " -").replace("/", " -").replace("*", " -").replace("?", " -")
					.replace("\"", " -").replace(">", " -").replace("<", " -").replace("|", " -");

			boolean hasSong = false;
			final Iterator<File> it = FileUtils.iterateFiles(new File(localFolder), null, true);
			while (it.hasNext()) {
				final File file = it.next();
				if (title.equalsIgnoreCase(FilenameUtils.getBaseName(file.getAbsolutePath()))) {
					hasSong = true;
					break;
				}
			}

			if (!hasSong) {
				System.out.println("\t" + title);
			}
		} else {
			System.out.println("Track is not streamable, no way to sync song from URL " + song);
		}
	}

	private CloudAPI getApi() {
		// Weird bug if you call the API more than 8 times it hangs indefinitely. So, create a new one every
		// 8th call to keep things rolling.
		apiCount++;
		if (apiCount % 8 == 0) {
			api = new ApiWrapper(Constants.CLIENT_ID, Constants.CLIENT_SECRET, null, null);
		}
		return api;
	}
}
