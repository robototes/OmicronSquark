package com.shsrobotics.omicronsquark.commands;

public class UnDeployPistons extends CommandBase {
	
	public UnDeployPistons() {
		requires(climber);
	}

	protected void initialize() {
		climber.retract();
	}

	protected void execute() { }

	protected boolean isFinished() {
		return false;
	}

	protected void end() { }

	protected void interrupted() { }
}
