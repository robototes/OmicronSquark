package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class DriveToY extends CommandBase implements Maps {

	private double goal;

	public DriveToY(double y) {
		requires(driveTrain);

		goal = y;
	}

	protected void initialize() {
		driveTrain.driveY(goal);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return (driveTrain.distanceLeft() < Constants.significanceLevel_Distance);
	}

	protected void end() { }

	protected void interrupted() {
		driveTrain.disable();
		driveTrain.stop();
	}
}
