package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class DriveForTime extends CommandBase implements Maps {
	private final double x;
	private final double y;
	private final double z;
	
	public DriveForTime(int x, int y, int z, double time) {
		this.x = x;
		this.y = y;
		this.z = z;
		requires(driveTrain);
		setTimeout(time);
	}

	protected void initialize() { }

	protected void execute() {
		driveTrain.drive(x, y, z, true); // drive using gyro
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		driveTrain.stop();
	}

	protected void interrupted() {
		driveTrain.stop();
	}
}
