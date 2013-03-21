package com.shsrobotics.omicronsquark.commands;

public class Override extends CommandBase {
	
	public Override() {
		requires(diskShooter);
		setInterruptible(true);
	}

	protected void initialize() {
		diskShooter.setRaw(1.0);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		diskShooter.set(0.0);
	}

	protected void interrupted() {
		diskShooter.set(0.0);
	}
}
