package com.github.dwiechert.sc.util.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.github.dwiechert.download.Downloader;
import com.github.dwiechert.sc.util.SCUtilFactory;

public class DownloadCommand extends Command {
	private static final String SONG_SHORT = "s";
	private static final String SONG_LONG = "songUrl";
	private static final String ARTIST_SHORT = "a";
	private static final String ARTIST_LONG = "artistUrl";
	private static final String PLAYLIST_SHORT = "p";
	private static final String PLAYLIST_LONG = "playlistUrl";
	private static final String FOLDER_SHORT = "o";
	private static final String FOLDER_LONG = "outputFolder";

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
		options.addOption(PLAYLIST_SHORT, PLAYLIST_LONG, true, "Downloads all songs from the specified playlist.");
		options.addOption(FOLDER_SHORT, FOLDER_LONG, true, "Folder to download the songs to (defaults to current directory).");
		return options;
	}

	@Override
	public void run(final String... args) {
		final CommandLine line = parseArguments(args);
		final String destinationFolder = line.getOptionValue(FOLDER_SHORT, System.getProperty("user.dir"));

		// FIXME: Have some way to add Mp3Metadata for individual songs/artists
		if (line.hasOption(SONG_SHORT)) {
			for (final String song : line.getOptionValues(SONG_SHORT)) {
				final Downloader downloader = SCUtilFactory.getDownloader(song);
				downloader.downloadSong(song, destinationFolder, null);
			}
		}

		if (line.hasOption(ARTIST_SHORT)) {
			for (final String artist : line.getOptionValues(ARTIST_SHORT)) {
				final Downloader downloader = SCUtilFactory.getDownloader(artist);
				downloader.downloadArtist(artist, destinationFolder, null);
			}
		}
		
		if (line.hasOption(PLAYLIST_SHORT)) {
			for (final String playlist : line.getOptionValues(PLAYLIST_SHORT)) {
				final Downloader downloader = SCUtilFactory.getDownloader(playlist);
				downloader.downloadPlaylist(playlist, destinationFolder, null);
			}
		}
	}
}
