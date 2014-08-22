package com.github.dwiechert.sc.util.commands;

public abstract class Command {
	public abstract String getName();
	
	public abstract String getDescription();
	
	public abstract void run(String... args);
}
