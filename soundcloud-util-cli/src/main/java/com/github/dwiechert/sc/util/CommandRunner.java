package com.github.dwiechert.sc.util;

import java.util.Map;
import java.util.TreeMap;

import com.github.dwiechert.sc.util.commands.Command;

public class CommandRunner {
	private final Map<String, Command> commands = new TreeMap<>();

	public void addCommand(final Command command) {
		commands.put(command.getName(), command);
	}

	public void run(final String... args) {
		final String commandName = args[0];
		if (!commands.containsKey(commandName)) {
			displayHelp();
			return;
		}
		
		final String[] newArgs = new String[args.length - 1];
		System.arraycopy(args, 1, newArgs, 0, newArgs.length);
		
		final Command command = commands.get(commandName);
		command.run(newArgs);
	}

	private void displayHelp() {
		// TODO Auto-generated method stub
		
	}
}
