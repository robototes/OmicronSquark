package com.shsrobotics.omicronsquark.commands;

public class RaiseBlocker extends CommandBase {
	
	public RaiseBlocker() {
		requires(blocker);
	}

	protected void initialize() {
		blocker.raise();
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
