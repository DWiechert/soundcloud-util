package com.github.dwiechert.sc.util.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;

import com.github.dwiechert.sc.util.models.FolderConfig;
import com.github.dwiechert.sc.util.models.SyncConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

	private void save(final SyncConfig config, final String configFile) {
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		final String json = gson.toJson(config);
		try {
			FileUtils.write(new File(configFile), json);
		} catch (final IOException e) {
			throw new RuntimeException("Error writing config file.", e);
		}
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
}
