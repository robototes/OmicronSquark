package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class RotateTo extends CommandBase implements Maps {

	private double goal;

	public RotateTo(double angle) {
		requires(driveTrain);

		goal = angle;
	}

	protected void initialize() {
		driveTrain.rotateTo(goal);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return (driveTrain.distanceLeft() < Constants.significanceLevel_Angle);
	}

	protected void end() { }

	protected void interrupted() {
		driveTrain.disable();
		driveTrain.stop();
	}
}
