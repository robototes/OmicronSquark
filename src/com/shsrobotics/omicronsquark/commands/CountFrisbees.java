package com.shsrobotics.omicronsquark.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CountFrisbees extends CommandBase {
	
	protected void initialize() {
		SmartDashboard.putNumber("Number of Frisbees", SmartDashboard.getNumber("Number of Frisbees") + 0.5);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}
