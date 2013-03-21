package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BringWheelsToSpeedForPyramidSide extends CommandBase implements Maps {
	
	public BringWheelsToSpeedForPyramidSide() {
		requires(diskShooter);
		setInterruptible(true);
	}

	protected void initialize() {
		double fudgeFactor = - Constants.dialMaximumChangePercentage *
		shooterJoystick.getRawAxis(Maps.Constants.towerSideFudgeFactor);
		double value = Constants.defaultShootingNextToPyramidValue + fudgeFactor;
		diskShooter.set(value);
		SmartDashboard.putNumber("Flywheel Value", value);
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
