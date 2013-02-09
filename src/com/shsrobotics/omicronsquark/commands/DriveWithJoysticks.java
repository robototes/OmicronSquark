package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class DriveWithJoysticks extends CommandBase implements Maps {

	public DriveWithJoysticks() {
		requires(driveTrain);
	}

	protected void initialize() {
        ;
    }

	protected void execute() {
		driveTrain.drive(oi.getX(), oi.getY(), oi.getZ());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
        ;
    }

	protected void interrupted() {
		driveTrain.drive(0.0, 0.0, 0.0);
	}
}
