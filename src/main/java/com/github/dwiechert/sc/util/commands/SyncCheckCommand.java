package com.github.dwiechert.sc.util.commands;

import java.io.File;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.github.dwiechert.sc.util.models.SyncConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SyncCheckCommand extends AbstractSyncCommand {
	@Override
	public String getName() {
		return "syncCheck";
	}

	@Override
	public String getDescription() {
		return "Checks to see what songs you don't have synced from SoundCloud per artist in the scsync.config file.";
	}

	@Override
	public Options getOptions() {
		return super.getOptions();
	}

	@Override
	public void run(final String... args) {
		final CommandLine line = parseArguments(args);
		final String configFile = getConfigFile(line);
		final SyncConfig config = readConfig(configFile);
	}

	private SyncConfig readConfig(final String configFile) {
		final Gson gson = new GsonBuilder().create();
		try {
			return gson.fromJson(new FileReader(new File(configFile)), SyncConfig.class);
		} catch (final Exception e) {
			throw new RuntimeException("Error reading " + configFile + ".", e);
		}
	}
}
