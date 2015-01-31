package com.github.dwiechert.sync.impls;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.github.dwiechert.download.Downloader;
import com.github.dwiechert.sc.util.Constants;
import com.github.dwiechert.sc.util.SCUtilFactory;
import com.github.dwiechert.sc.util.models.FolderConfig;
import com.github.dwiechert.sc.util.models.SongConfig;
import com.github.dwiechert.sync.Syncer;
import com.soundcloud.api.ApiWrapper;
import com.soundcloud.api.CloudAPI;
import com.soundcloud.api.Request;

public class SCSyncer implements Syncer {
	private CloudAPI api = new ApiWrapper(Constants.CLIENT_ID, Constants.CLIENT_SECRET, null, null);
	private int apiCount = 0;

	@Override
	public void sync(final FolderConfig folderConfig) {
		for (final SongConfig song : folderConfig.getSongs()) {
			if (song.isSyncOn()) {
				downloadSong(song, folderConfig);
			}
		}
	}

	private void downloadSong(final SongConfig song, final FolderConfig folderConfig) {
		String trackString = null;
		try {
			final HttpResponse trackResponse = getApi().get(new Request(String.format(Constants.TRACK_URL, song.getTrackId(), Constants.CLIENT_ID)));
			trackString = EntityUtils.toString(trackResponse.getEntity());
		} catch (final Exception e) {
			e.printStackTrace();
		}

		final JSONObject obj = new JSONObject(trackString);
		final boolean streamable = obj.getBoolean("streamable");

		if (streamable) {
			final Downloader downloader = SCUtilFactory.getDownloader(song.getSongUrl());
			downloader.downloadSong(obj.getString("permalink_url"), folderConfig.getDownloadFolder(), folderConfig.getMp3Metadata());
			song.setSyncOn(false);
		} else {
			System.out.println("Track is not streamable, no way to sync song from URL " + song.getSongUrl());
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
