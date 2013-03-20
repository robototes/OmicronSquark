package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.*;

public class DeployPistons extends CommandBase implements Maps {
	
	public DeployPistons() {
		requires(climber);
	}

	protected void initialize() {
		if (Global.currentDriverStationMode == Maps.Constants.climbMode) {
		climber.extend();
		} else {
			end();
		}
	}

	protected void execute() { }

	protected boolean isFinished() {
		return false;
	}

	protected void end() { }

	protected void interrupted() { }
}
