package com.shsrobotics.omicronsquark.commands;

public class LowerBlocker extends CommandBase {
	
	public LowerBlocker() {
		requires(blocker);
	}

	protected void initialize() {
		blocker.lower();
	}

	protected void execute() { }

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		interrupted();
	}

	protected void interrupted() {
		blocker.stop();
	}
}
