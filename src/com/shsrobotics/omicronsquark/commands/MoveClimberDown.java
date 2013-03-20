package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.*;

public class MoveClimberDown extends CommandBase implements Maps {
	
	public MoveClimberDown() {
		requires(climber);
	}

	protected void initialize() {
		if (Global.currentDriverStationMode == Maps.Constants.climbMode) {
			climber.set(1.0);
		} else {
			end();
		}
	}

	protected void execute() { }

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		climber.stop();
	}

	protected void interrupted() {
		climber.stop();
	}
}
