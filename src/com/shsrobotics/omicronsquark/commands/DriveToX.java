package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class DriveToX extends CommandBase implements Maps {

	private double goal;

	public DriveToX(double x) {
		requires(driveTrain);

		goal = x;
	}

	protected void initialize() {
		driveTrain.driveX(goal);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return (driveTrain.distanceLeft() < Maps.Constants.significanceLevel_Distance);
	}

	protected void end() { }

	protected void interrupted() {
		driveTrain.disable();
		driveTrain.stop();
	}
}
