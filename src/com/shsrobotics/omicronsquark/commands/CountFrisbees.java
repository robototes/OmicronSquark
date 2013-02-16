package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CountFrisbees extends CommandBase implements Maps {
	
	protected void initialize() {
		Global.currentFrisbeeCount += 0.5;
		SmartDashboard.putNumber("Number of Frisbees", Global.currentFrisbeeCount);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}
