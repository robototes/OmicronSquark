package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.*;

public class MoveClimberDown extends CommandBase implements Maps {
	
	public MoveClimberDown() {
		requires(climber);
	}

	protected void initialize() {
		climber.set(1.0);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return climber.hasReachedLowerClimbBound();
	}

	protected void end() {
		climber.stop();
	}

	protected void interrupted() {
		climber.stop();
	}
}
