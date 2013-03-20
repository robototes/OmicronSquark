package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
import com.shsrobotics.omicronsquark.Maps;

public class BringWheelsToSpeedForPyramidBack extends CommandBase implements Maps {
	
	public BringWheelsToSpeedForPyramidBack() {
		requires(diskShooter);
		setInterruptible(true);
	}

	protected void initialize() {
		if (Global.currentDriverStationMode == Constants.shootMode) {
		double fudgeFactor = Maps.Constants.dialMaximumChangePercentage *
			shooterJoystick.getRawAxis(Maps.Constants.towerBackFudgeFactor);
		double value = Maps.Constants.defaultShootingBehindPyramidValue;
		diskShooter.set(value + fudgeFactor);
		} else {
			end();
		}
	}

	protected void execute() { }

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		diskShooter.set(0.0);
	}

	protected void interrupted() {
		diskShooter.set(0.0);
	}
}
