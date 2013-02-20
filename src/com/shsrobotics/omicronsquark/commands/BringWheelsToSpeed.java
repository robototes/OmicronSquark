package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class BringWheelsToSpeed extends CommandBase implements Maps {
	
	public BringWheelsToSpeed() {
		requires(diskShooter);
		setInterruptible(true);
	}

	protected void initialize() {
		double value = (1 - oi.getThrottle()) / 2;
		if (Math.abs(0.5 - value) < 0.1) { // within 5% of 50%
			value = 0.53;
		}
		diskShooter.set(value);
	}
	
	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}
