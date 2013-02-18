package com.shsrobotics.omicronsquark.commands;

public class ControlShooterWheelsWithThrottle extends CommandBase {
	
	public ControlShooterWheelsWithThrottle() {
		requires(diskShooter);
	}

	protected void initialize() { }
	
	protected void execute() {
		diskShooter.set(0.95 * (1 - oi.getThrottle()) / 2);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() { }

	protected void interrupted() { }
}
