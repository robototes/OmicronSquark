package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class LoaderReverse extends CommandBase implements Maps {
	
	public LoaderReverse() {
		requires(diskShooter);
	}

	protected void initialize() {
		diskShooter.setLoader(REVERSE);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return false;
	}

	protected void end() { }

	protected void interrupted() {
		diskShooter.setLoader(OFF);
	}
}