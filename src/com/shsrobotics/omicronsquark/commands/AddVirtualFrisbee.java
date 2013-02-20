package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AddVirtualFrisbee extends CommandBase {

	protected void initialize() {
		Global.currentFrisbeeCount++;
		SmartDashboard.putNumber("Number of Frisbees", Global.currentFrisbeeCount);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }	
}
