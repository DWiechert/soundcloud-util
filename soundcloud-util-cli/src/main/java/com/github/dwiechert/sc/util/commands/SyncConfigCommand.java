package com.github.dwiechert.sc.util.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.github.dwiechert.sc.util.models.FolderConfig;
import com.github.dwiechert.sc.util.models.Mp3Metadata;
import com.github.dwiechert.sc.util.models.SyncConfig;

public class SyncConfigCommand extends AbstractSyncCommand {
	@Override
	public String getName() {
		return "syncConfig";
	}

	@Override
	public String getDescription() {
		return "Creates or updates the sync config.";
	}

	@Override
	public Options getOptions() {
		return super.getOptions();
	}

	@Override
	public void run(final String... args) {
		final CommandLine line = parseArguments(args);
		final String configFile = getConfigFile(line);
		final SyncConfig config = getInput();
		save(config, configFile);
	}

	private SyncConfig getInput() {
		final SyncConfig config = new SyncConfig();

		boolean more = false;
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			do {
				final FolderConfig folderConfig = new FolderConfig();

				System.out.print("Enter the artist url to sync: ");
				final String artistUrl = reader.readLine();
				folderConfig.setArtistUrl(artistUrl);

				System.out.print("Enter the local folder to sync: ");
				final String localFolder = reader.readLine();
				folderConfig.setLocalFolder(localFolder);

				System.out.print("Enter the local folder to download to (hit enter for default [" + localFolder + "]: ");
				final String downloadFolder = reader.readLine();
				folderConfig.setDownloadFolder("".equals(downloadFolder) ? localFolder : downloadFolder);

				System.out.print("Would you like this folder to sync (y/n)?: ");
				final String syncOn = reader.readLine();
				folderConfig.setSyncOn("y".equalsIgnoreCase(syncOn) || "yes".equalsIgnoreCase(syncOn));
				
				System.out.print("Would you like to tag these files with mp3 metadata?: ");
				final String tagOn = reader.readLine();
				if ("y".equalsIgnoreCase(tagOn) || "yes".equalsIgnoreCase(tagOn)) {
					folderConfig.setMp3Metadata(getMp3Metadata(reader));
				}

				config.getConfigs().add(folderConfig);

				System.out.print("Would you like to sync any more folders (y/n)?: ");
				final String moreString = reader.readLine();
				more = "y".equalsIgnoreCase(moreString) || "yes".equalsIgnoreCase(moreString);
			} while (more);
		} catch (final Exception e) {
			throw new RuntimeException("Error getting user input.", e);
		}

		return config;
	}

	private Mp3Metadata getMp3Metadata(final BufferedReader reader) throws Exception {
		final Mp3Metadata metadata = new Mp3Metadata();
		
		// FIXME: Prompt with artist name
		System.out.print("Enter the artist: ");
		final String artist = reader.readLine();
		metadata.setArtist(artist);
		
		System.out.print("Enter the composer (hit enter for default [" + artist + "]: ");
		final String composer = reader.readLine();
		metadata.setComposer("".equals(composer) ? artist : composer);
		
		System.out.print("Enter the publisher (hit enter for default [" + artist + "]: ");
		final String publisher = reader.readLine();
		metadata.setPublisher("".equals(publisher) ? artist : publisher);
		
		System.out.print("Enter the original artist (hit enter for default [" + artist + "]: ");
		final String originalArtist = reader.readLine();
		metadata.setOriginalArtist("".equals(originalArtist) ? artist : originalArtist);
		
		System.out.print("Enter the album artist (hit enter for default [" + artist + "]: ");
		final String albumArtist = reader.readLine();
		metadata.setAlbumArtist("".equals(albumArtist) ? artist : albumArtist);
		
		System.out.print("Enter the album: ");
		final String album = reader.readLine();
		metadata.setAlbum(album);
		
		System.out.print("Enter the genre: ");
		final String genre = reader.readLine();
		metadata.setGenre(genre);
		
		System.out.println("Enter the copyright: ");
		final String copyright = reader.readLine();
		metadata.setCopyright(copyright);
		
		System.out.println("Enter the encoder: ");
		final String encoder = reader.readLine();
		metadata.setEncoder(encoder);
		
		return metadata;
	}
}
