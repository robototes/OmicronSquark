package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
import com.shsrobotics.omicronsquark.Maps;


public class ClimbMode extends CommandBase implements Maps {
	
	public ClimbMode() {
		
	}

	protected void initialize() {
		Global.currentDriverStationMode = Constants.climbMode;
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
