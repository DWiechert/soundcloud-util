package com.github.dwiechert.sc.util.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

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
}
