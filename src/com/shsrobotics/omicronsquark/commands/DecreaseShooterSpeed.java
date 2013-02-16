package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class DecreaseShooterSpeed extends CommandBase implements Maps {
	
	public DecreaseShooterSpeed() {
		requires(diskShooter);
	}

	protected void initialize() {
		diskShooter.increment(-1 * Constants.shooterSpeedIncrement);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}