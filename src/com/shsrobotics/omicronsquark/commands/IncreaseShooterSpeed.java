package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class IncreaseShooterSpeed extends CommandBase implements Maps {
	
	public IncreaseShooterSpeed() {
		requires(diskShooter);
	}

	protected void initialize() {
		diskShooter.increment(Constants.shooterSpeedIncrement);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}