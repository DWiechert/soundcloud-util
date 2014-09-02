package com.github.dwiechert.sc.util.commands;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public abstract class Command {
	public abstract String getName();

	public abstract String getDescription();

	public abstract Options getOptions();

	public abstract void run(String... args);

	protected CommandLine parseArguments(final String... args) {
		final CommandLineParser parser = new BasicParser();
		CommandLine line = null;
		try {
			line = parser.parse(getOptions(), args);
		} catch (final ParseException e) {
			System.err.println("Error parsing options - " + e);
			System.exit(-1);
		}
		return line;
	}
}
