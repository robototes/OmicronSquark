package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CountDisks extends CommandBase implements Maps {

	public CountDisks() {
		requires(camera);
	}

	protected void initialize() {
		camera.getDiskCount();
	}

	protected void execute() {
		if (camera.isDoneComputing()) {
			SmartDashboard.putInt("Number of Disks Near Robot", camera.getResult());
			camera.getDiskCount();
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() { }

	protected void interrupted() { }
}
