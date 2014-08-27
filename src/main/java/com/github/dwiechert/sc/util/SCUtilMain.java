package com.github.dwiechert.sc.util;

import com.github.dwiechert.sc.util.commands.DownloadCommand;
import com.github.dwiechert.sc.util.commands.SyncCheckCommand;
import com.github.dwiechert.sc.util.commands.SyncConfigCommand;

public class SCUtilMain {
	public static void main(final String[] args) {
		final CommandRunner runner = new CommandRunner();
		runner.addCommand(new DownloadCommand());
		runner.addCommand(new SyncConfigCommand());
		runner.addCommand(new SyncCheckCommand());
		runner.run(args);
	}
}
