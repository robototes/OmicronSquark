package com.shsrobotics.omicronsquark.commands;

public class ZeroFlywheels extends CommandBase {
	
	public ZeroFlywheels() {
		requires(diskShooter);
	}

	protected void initialize() {
		diskShooter.set(0.0);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}
