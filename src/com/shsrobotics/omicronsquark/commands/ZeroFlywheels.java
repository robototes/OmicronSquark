package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class ZeroFlywheels extends CommandBase implements Maps {
	
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