package com.shsrobotics.omicronsquark.commands;

public class TurnOnLoader extends CommandBase {
	private boolean hasLeft;
	
	public TurnOnLoader() {
		requires(diskShooter);
	}

	protected void initialize() {
		hasLeft = false;
		diskShooter.load();
	}

	protected void execute() {
		if (!diskShooter.get() && !hasLeft) {
			hasLeft = true;
		}
	}
	
	protected boolean isFinished() {
		return diskShooter.get() && hasLeft;
	}

	protected void end() {
		diskShooter.stop();
	}

	protected void interrupted() {
		diskShooter.stop();
	}
}
