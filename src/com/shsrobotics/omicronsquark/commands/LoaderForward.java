package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.DriverStation;

public class LoaderForward extends CommandBase implements Maps {
	
	public LoaderForward() {
		requires(diskShooter);
	}

	protected void initialize() {
		if (DriverStation.getInstance().isAutonomous()) {
			diskShooter.loadAutonomous();
		} else {
			diskShooter.loadTeleoperated();
		}
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}