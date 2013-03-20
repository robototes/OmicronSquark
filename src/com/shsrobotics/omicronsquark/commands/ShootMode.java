package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
import com.shsrobotics.omicronsquark.Maps;


public class ShootMode extends CommandBase implements Maps {
	
	public ShootMode() {
		
	}

	protected void initialize() {
		Global.currentDriverStationMode = Constants.shootMode;
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
