package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BringWheelsToSpeedForPyramidBack extends CommandBase implements Maps {
	
	public BringWheelsToSpeedForPyramidBack() {
		requires(diskShooter);
		setInterruptible(true);
	}

	protected void initialize() { }

	protected void execute() {
		double fudgeFactor = -0.025 - Constants.dialMaximumChangePercentage *
		shooterJoystick.getRawAxis(Maps.Constants.towerBackFudgeFactor);
		double value = Constants.defaultShootingBehindPyramidValue + fudgeFactor;
		diskShooter.set(value);
		SmartDashboard.putNumber("Flywheel Value", value);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		diskShooter.stopWheels();
	}

	protected void interrupted() {
		diskShooter.stopWheels();
	}
}
