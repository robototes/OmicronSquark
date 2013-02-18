package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class Idle extends CommandBase implements Maps {
	
	public Idle() {
		requires(diskShooter);
	}

	protected void initialize() {
		diskShooter.set(Constants.idlePercent);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return false;
	}

	protected void end() { }

	protected void interrupted() {
		diskShooter.set(0.0);
	}
}
