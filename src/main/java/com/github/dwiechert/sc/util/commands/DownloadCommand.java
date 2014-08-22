package com.github.dwiechert.sc.util.commands;

public class DownloadCommand extends Command {
	@Override
	public String getName() {
		return "download";
	}

	@Override
	public String getDescription() {
		return "Downloads songs from either a specific song url or all songs from a particular artist.";
	}

	@Override
	public void run(final String... args) {
		System.out.println("Download command is running.");
		System.out.println("Arguments are:");
		for (final String arg : args) {
			System.out.print(arg + " ");
		}
	}
}
