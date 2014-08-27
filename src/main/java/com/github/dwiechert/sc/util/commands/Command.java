package com.github.dwiechert.sc.util.commands;

import org.apache.commons.cli.Options;

import com.github.dwiechert.sc.util.Constants;
import com.soundcloud.api.ApiWrapper;
import com.soundcloud.api.CloudAPI;

public abstract class Command {
	public abstract String getName();

	public abstract String getDescription();

	public abstract void run(String... args);

	public abstract Options getOptions();

	protected final CloudAPI api = new ApiWrapper(Constants.CLIENT_ID, Constants.CLIENT_SECRET, null, null);
}
