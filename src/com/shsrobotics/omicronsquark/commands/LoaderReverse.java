package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
import com.shsrobotics.omicronsquark.Maps;

public class LoaderReverse extends CommandBase implements Maps {
	
	public LoaderReverse() {
		requires(diskShooter);
	}

	protected void initialize() {
		if (Global.currentDriverStationMode == Constants.climbMode) {
			diskShooter.setLoader(REVERSE);
		} else {
			new LoaderOff().start();
		}
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}