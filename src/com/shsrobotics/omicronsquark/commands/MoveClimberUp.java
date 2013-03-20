package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.*;

public class MoveClimberUp extends CommandBase implements Maps{
	
		
	public MoveClimberUp() {
		requires(climber);
	}

	protected void initialize() {
		if (Global.currentDriverStationMode == Constants.climbMode) {
			climber.set(-1.0);
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
