package com.github.dwiechert.sc.util.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;

import com.github.dwiechert.sc.util.models.FolderConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SyncConfig extends Command {
	private static final String CONFIG_FILE_SHORT = "c";
	private static final String CONFIG_FILE_LONG = "configFile";
	private static final String CONFIG_DEFAULT = "sc-sync.config";

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

		final List<FolderConfig> configs = new ArrayList<>();
		getInput(configs);
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

	private void getInput(final List<FolderConfig> configs) {
		// TODO Auto-generated method stub

	}
}
