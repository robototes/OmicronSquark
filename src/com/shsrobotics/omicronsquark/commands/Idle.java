package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
import com.shsrobotics.omicronsquark.Maps;

public class Idle extends CommandBase implements Maps {
	
	public Idle() {
		requires(diskShooter);
	}

	protected void initialize() {
		if (Global.currentDriverStationMode == Constants.shootMode) {
			diskShooter.set(Constants.idlePercent);
		} else {
			new ZeroFlywheels().start();
		}
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}
