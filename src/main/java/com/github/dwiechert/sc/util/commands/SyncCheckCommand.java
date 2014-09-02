package com.github.dwiechert.sc.util.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import com.github.dwiechert.sc.util.SCUtilFactory;
import com.github.dwiechert.sc.util.models.FolderConfig;
import com.github.dwiechert.sc.util.models.SyncConfig;
import com.github.dwiechert.syncCheck.SyncChecker;

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

		for (final FolderConfig folderConfig : config.getConfigs()) {
			final SyncChecker checker = SCUtilFactory.getSyncChecker(folderConfig.getArtistUrl());
			checker.check(folderConfig);
		}
	}
}
