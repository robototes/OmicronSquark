package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.subsystems.Camera.Angles;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CheckShootingDistance extends CommandBase implements Maps {
	private int executeCount;
	
	public CheckShootingDistance() {
		requires(camera);
	}

	protected void initialize() { }

	protected void execute() {
		if (executeCount % 10 == 0) { // every 10 times
			double angle = camera.getAlignmentAngles().vertical;
			if (angle != Double.NEGATIVE_INFINITY) {
				if (Math.abs(angle) < Constants.significanceLevel_Angle) { // right on
					SmartDashboard.putString("Robot Position", "On Target");
				} else if (angle < 0.0) { // too far
					SmartDashboard.putString("Robot Position", "Too Far");
				} else if (angle > 0.0) { // too close
					SmartDashboard.putString("Robot Position", "Too Close");
				}
			} else {
				SmartDashboard.putString("Robot Position", "NO GOAL");
			}
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() { }

	protected void interrupted() { }
}
