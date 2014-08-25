package com.github.dwiechert.sc.util.commands;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.github.dwiechert.sc.util.Constants;
import com.soundcloud.api.Request;

public class DownloadCommand extends Command {
	private static final String SONG_SHORT = "s";
	private static final String SONG_LONG = "songUrl";
	private static final String ARTIST_SHORT = "a";
	private static final String ARTIST_LONG = "artistUrl";
	private static final String FOLDER_SHORT = "f";
	private static final String FOLDER_LONG = "folder";

	@Override
	public String getName() {
		return "download";
	}

	@Override
	public String getDescription() {
		return "Downloads songs from either a specific song url or all songs from a particular artist.";
	}

	@Override
	public Options getOptions() {
		final Options options = new Options();
		options.addOption(SONG_SHORT, SONG_LONG, true, "Downloads an individual song.");
		options.addOption(ARTIST_SHORT, ARTIST_LONG, true, "Downloads all songs from the specified artist.");
		options.addOption(FOLDER_SHORT, FOLDER_LONG, true, "Folder to download the songs to (defaults to current directory).");
		return options;
	}

	@Override
	public void run(final String... args) {
		final CommandLineParser parser = new BasicParser();
		CommandLine line = null;
		try {
			line = parser.parse(getOptions(), args);
		} catch (final ParseException e) {
			System.err.println("Error parsing options - " + e);
			System.exit(-1);
		}

		System.out.println();
		if (line.hasOption(SONG_SHORT)) {
			for (final String song : line.getOptionValues(SONG_SHORT)) {
				String trackString = null;
				try {
					final long trackId = api.resolve(song);
					final HttpResponse trackResponse = api.get(new Request(String.format(Constants.TRACK_URL, trackId, Constants.CLIENT_ID)));
					trackString = EntityUtils.toString(trackResponse.getEntity());
				} catch (final Exception e) {
					e.printStackTrace();
				}

				final JSONObject obj = new JSONObject(trackString);
				final boolean streamable = obj.getBoolean("streamable");

				if (streamable) {
					try {
						final String streamurl = obj.getString("stream_url");
						final String title = obj.getString("title");

						final URL url = new URL(streamurl + "?client_id=" + Constants.CLIENT_ID);
						final File mp3 = new File(title + ".mp3");
						System.out.println("Starting to download track " + title);
						IOUtils.copyLarge(url.openStream(), new FileOutputStream(mp3));
						System.out.println("Finished downloading track " + title);
					} catch (final Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Track is not streamable, no way to download song from URL " + song);
				}
			}
		}

		System.out.println();
		if (line.hasOption(ARTIST_SHORT)) {
			for (final String artist : line.getOptionValues(ARTIST_SHORT)) {
				System.out.print(artist + "\t");
			}
		}

		System.out.println();
		if (line.hasOption(FOLDER_SHORT)) {
			for (final String folder : line.getOptionValues(FOLDER_SHORT)) {
				System.out.print(folder + "\t");
			}
		}
	}
}
