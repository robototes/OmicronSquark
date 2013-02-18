package com.shsrobotics.omicronsquark.commands;

public class DeployPiston extends CommandBase {
	
	public DeployPiston() {
		requires(climber);
	}

	protected void initialize() {
		if (climber.get()) {
			climber.retract();
		} else {
			climber.extend();
		}
	}

	protected void execute() { }

	protected boolean isFinished() {
		return false;
	}

	protected void end() { }

	protected void interrupted() { }
}
