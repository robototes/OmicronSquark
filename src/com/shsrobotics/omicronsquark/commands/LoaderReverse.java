package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class LoaderReverse extends CommandBase implements Maps {
	
	public LoaderReverse() {
		requires(diskShooter);
	}

	protected void initialize() {
		diskShooter.setLoader(-1.0);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}