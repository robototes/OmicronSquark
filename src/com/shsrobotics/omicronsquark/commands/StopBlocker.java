package com.shsrobotics.omicronsquark.commands;

public class StopBlocker extends CommandBase {
	
	public StopBlocker() {
		requires(blocker);
	}

	protected void initialize() {
		blocker.stop();
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}
