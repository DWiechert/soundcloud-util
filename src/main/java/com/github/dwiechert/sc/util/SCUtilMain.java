package com.github.dwiechert.sc.util;

import com.github.dwiechert.sc.util.commands.DownloadCommand;

public class SCUtilMain {
	public static void main(final String[] args) {
		final CommandRunner runner = new CommandRunner();
		runner.addCommand(new DownloadCommand());
		runner.run(args);
	}
}
