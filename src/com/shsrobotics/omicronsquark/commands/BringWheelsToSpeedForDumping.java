package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class BringWheelsToSpeedForDumping extends CommandBase implements Maps {
	
	public BringWheelsToSpeedForDumping() {
		requires(diskShooter);
		setInterruptible(true);
	}

	protected void initialize() {
		double fudgeFactor = Maps.Constants.dialMaximumChangePercentage * shooterJoystick.getRawAxis(Constants.dumpFudgeFactor);
		double value = Maps.Constants.defaultDumpingValue;
		diskShooter.set(value + fudgeFactor);
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