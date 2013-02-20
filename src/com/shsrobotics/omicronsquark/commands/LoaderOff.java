package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class LoaderOff extends CommandBase implements Maps {
	
	public LoaderOff() {
		requires(diskShooter);
	}

	protected void initialize() {
		diskShooter.setLoader(OFF);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}