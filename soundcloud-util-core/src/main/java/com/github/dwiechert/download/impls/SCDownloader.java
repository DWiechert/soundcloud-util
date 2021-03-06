package com.github.dwiechert.download.impls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.github.dwiechert.download.Downloader;
import com.github.dwiechert.mp3.Mp3Tagger;
import com.github.dwiechert.sc.util.Constants;
import com.github.dwiechert.sc.util.models.Mp3Metadata;
import com.soundcloud.api.ApiWrapper;
import com.soundcloud.api.CloudAPI;
import com.soundcloud.api.Request;

public class SCDownloader implements Downloader {
	private CloudAPI api = new ApiWrapper(Constants.CLIENT_ID, Constants.CLIENT_SECRET, null, null);
	private int apiCount = 0;

	@Override
	public void downloadPlaylist(final String playlistUrl, final String destinationFolder, final Mp3Metadata metadata) {
		String playlistString = null;
		try {
			final long setId = getApi().resolve(playlistUrl);
			final HttpResponse setResponse = getApi().get(new Request(String.format(Constants.PLAYLIST_URL, setId, Constants.CLIENT_ID)));
			playlistString = EntityUtils.toString(setResponse.getEntity());
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
		final JSONObject obj = new JSONObject(playlistString);
		final JSONArray array = obj.getJSONArray("tracks");
		for (int i = 0; i < array.length(); i++) {
			final JSONObject obj2 = array.getJSONObject(i);
			downloadSong(obj2.getString("permalink_url"), destinationFolder, metadata);
		}
	}

	@Override
	public void downloadArtist(final String artistUrl, final String destinationFolder, final Mp3Metadata metadata) {
		String artistString = null;
		try {
			final long artistId = getApi().resolve(artistUrl);
			final HttpResponse artistResponse = getApi().get(new Request(String.format(Constants.ARTIST_TRACK_URL, artistId, Constants.CLIENT_ID)));
			artistString = EntityUtils.toString(artistResponse.getEntity());
		} catch (final Exception e) {
			e.printStackTrace();
		}

		final JSONArray array = new JSONArray(artistString);
		for (int i = 0; i < array.length(); i++) {
			final JSONObject obj = array.getJSONObject(i);
			downloadSong(obj.getString("permalink_url"), destinationFolder, metadata);
		}
	}

	@Override
	public String downloadSong(final String songUrl, final String destinationFolder, final Mp3Metadata metadata) {
		String trackString = null;
		try {
			final long trackId = getApi().resolve(songUrl);
			final HttpResponse trackResponse = getApi().get(new Request(String.format(Constants.TRACK_URL, trackId, Constants.CLIENT_ID)));
			trackString = EntityUtils.toString(trackResponse.getEntity());
		} catch (final Exception e) {
			e.printStackTrace();
		}

		final JSONObject obj = new JSONObject(trackString);
		final boolean streamable = obj.getBoolean("streamable");

		if (streamable) {
			try {
				final String streamurl = obj.getString("stream_url");
				// Replace all illegal characters with 'space hyphen'
				final String title = obj.getString("title").replace(":", " -").replace("\\", " -").replace("/", " -").replace("*", " -").replace("?", " -")
						.replace("\"", " -").replace(">", " -").replace("<", " -").replace("|", " -");

				final URL url = new URL(streamurl + "?client_id=" + Constants.CLIENT_ID);
				
				final String endFilename = destinationFolder + File.separatorChar + title + ".mp3";
				final File mp3 = getDownloadFilename(endFilename, metadata != null);
				
				System.out.println("Starting to download track " + title);
				IOUtils.copyLarge(url.openStream(), new FileOutputStream(mp3));
				System.out.println("Finished downloading track " + title);
				
				if (metadata != null) {
					// Copy original so we can change values for each song
					final Mp3Metadata newMetadata = new Mp3Metadata(metadata);
					newMetadata.setTitle(title);
					newMetadata.setUrl(songUrl);
					Mp3Tagger.tagMp3(mp3.getAbsolutePath(), endFilename, newMetadata);
				}
				
				return title;
			} catch (final Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Track is not streamable, no way to download song from URL " + songUrl);
		}
		
		return "";
	}
	
	private File getDownloadFilename(final String endFilename, final boolean needTemp) throws IOException {
		if (!needTemp) {
			return new File(endFilename);
		}
		
		final String baseName = endFilename.substring(0, endFilename.lastIndexOf(".mp3"));
		final File file = File.createTempFile(baseName + "-temp", ".mp3");
		file.deleteOnExit();
		return file;
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
