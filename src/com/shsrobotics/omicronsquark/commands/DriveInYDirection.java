package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class DriveInYDirection extends CommandBase implements Maps {
	
	public DriveInYDirection() {
		requires(driveTrain);
	}

	protected void initialize() { }

	protected void execute() {
		driveTrain.drive(0.0, 1.0, 0.0, true);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() { }

	protected void interrupted() {
		driveTrain.stop();
	}
}
