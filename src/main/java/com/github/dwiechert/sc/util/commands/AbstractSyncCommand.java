package com.github.dwiechert.sc.util.commands;

import java.io.File;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.github.dwiechert.sc.util.models.SyncConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractSyncCommand extends Command {
	protected static final String CONFIG_FILE_SHORT = "c";
	protected static final String CONFIG_FILE_LONG = "configFile";
	protected static final String CONFIG_DEFAULT = "scsync.config";

	@Override
	public Options getOptions() {
		final Options options = new Options();
		options.addOption(CONFIG_FILE_SHORT, CONFIG_FILE_LONG, true, "Path to sync config file.");
		return options;
	}

	protected String getConfigFile(final CommandLine line) {
		return line.getOptionValue(CONFIG_FILE_SHORT, CONFIG_DEFAULT);
	}

	protected SyncConfig readConfig(final String configFile) {
		final Gson gson = new GsonBuilder().create();
		try {
			return gson.fromJson(new FileReader(new File(configFile)), SyncConfig.class);
		} catch (final Exception e) {
			throw new RuntimeException("Error reading " + configFile + ".", e);
		}
	}
}
