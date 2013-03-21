package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
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
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}
