package com.github.dwiechert.sc.util.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;

import com.github.dwiechert.sc.util.models.FolderConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SyncConfigCommand extends Command {
	private static final String CONFIG_FILE_SHORT = "c";
	private static final String CONFIG_FILE_LONG = "configFile";
	private static final String CONFIG_DEFAULT = "scsync.config";

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
		final Options options = new Options();
		options.addOption(CONFIG_FILE_SHORT, CONFIG_FILE_LONG, true, "Path to sync config file.");
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

		final String configFile = line.getOptionValue(CONFIG_FILE_SHORT, CONFIG_DEFAULT);

		final List<FolderConfig> configs = getInput();
		save(configs, configFile);
	}

	private void save(final List<FolderConfig> configs, final String configFile) {
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		final String json = gson.toJson(configs);
		try {
			FileUtils.write(new File(configFile), json);
		} catch (final IOException e) {
			throw new RuntimeException("Error writing config file.", e);
		}
	}

	private List<FolderConfig> getInput() {
		final List<FolderConfig> configs = new ArrayList<>();

		boolean more = false;
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			do {
				final FolderConfig config = new FolderConfig();

				System.out.print("Enter the artist url to sync: ");
				final String artistUrl = reader.readLine();
				config.setArtistUrl(artistUrl);

				System.out.print("Enter the local folder to sync: ");
				final String localFolder = reader.readLine();
				config.setLocalFolder(localFolder);

				System.out.print("Enter the local folder to download to (hit enter for default [" + localFolder + "]: ");
				final String downloadFolder = reader.readLine();
				config.setDownloadFolder("".equals(downloadFolder) ? localFolder : downloadFolder);

				System.out.print("Would you like this folder to sync (y/n)?: ");
				final String syncOn = reader.readLine();
				config.setSyncOn("y".equalsIgnoreCase(syncOn) || "yes".equalsIgnoreCase(syncOn));

				configs.add(config);

				System.out.print("Would you like to sync any more folders (y/n)?: ");
				final String moreString = reader.readLine();
				more = "y".equalsIgnoreCase(moreString) || "yes".equalsIgnoreCase(moreString);
			} while (more);
		} catch (final Exception e) {
			throw new RuntimeException("Error getting user input.", e);
		}

		return configs;
	}
}