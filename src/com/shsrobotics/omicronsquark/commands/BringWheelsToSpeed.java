package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;

public class BringWheelsToSpeed extends CommandBase {
	
	public BringWheelsToSpeed() {
		requires(diskShooter);
	}

	protected void initialize() {
		diskShooter.set((1 - oi.getThrottle()) / 2);
	}
	
	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}
