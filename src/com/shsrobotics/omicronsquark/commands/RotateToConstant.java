package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;

public class RotateToConstant extends CommandBase implements Maps {
	
	public RotateToConstant() {
		requires(driveTrain);
	}

	protected void initialize() {
		driveTrain.reset();
		driveTrain.rotateTo(20);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return driveTrain.onTarget();
	}

	protected void end() {
		driveTrain.stop();
	}

	protected void interrupted() {
		driveTrain.stop();
	}
}
