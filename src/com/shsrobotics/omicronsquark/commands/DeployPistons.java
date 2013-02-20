package com.shsrobotics.omicronsquark.commands;

public class DeployPistons extends CommandBase {
	
	public DeployPistons() {
		requires(climber);
	}

	protected void initialize() {
		climber.extend();
	}

	protected void execute() { }

	protected boolean isFinished() {
		return false;
	}

	protected void end() { }

	protected void interrupted() { }
}
