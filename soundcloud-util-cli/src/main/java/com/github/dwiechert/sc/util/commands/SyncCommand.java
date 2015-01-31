package com.github.dwiechert.sc.util.commands;

import org.apache.commons.cli.CommandLine;

import com.github.dwiechert.sc.util.SCUtilFactory;
import com.github.dwiechert.sc.util.models.FolderConfig;
import com.github.dwiechert.sc.util.models.SyncConfig;
import com.github.dwiechert.sync.Syncer;

public class SyncCommand extends AbstractSyncCommand {
	@Override
	public String getName() {
		return "sync";
	}

	@Override
	public String getDescription() {
		return "Syncs all of the music from the scsync.config file.";
	}

	@Override
	public void run(final String... args) {
		final CommandLine line = parseArguments(args);
		final String configFile = getConfigFile(line);
		final SyncConfig config = readConfig(configFile);

		for (final FolderConfig folderConfig : config.getConfigs()) {
			if (!folderConfig.isSyncOn()) {
				System.out.println("Sync is turned off for artist URL " + folderConfig.getArtistUrl() + ". Will not run sync for this artist.");
				continue;
			}
			final Syncer syncer = SCUtilFactory.getSyncer(folderConfig.getArtistUrl());
			syncer.sync(folderConfig);
		}
		
		save(config, configFile);
	}
}
