package edu.mum.framework;

import edu.mum.framework.command.ICommand;

// This is invoker
public class CommandManager {

	private ICommand command;
	
	public CommandManager(ICommand command) {
		this.command = command;
	}
	
	public void setCommand(ICommand command) {
		this.command = command;
	}
	
	public void submit() {
		command.execute();
	}
}
