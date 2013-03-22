package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.*;

public class MoveClimberUp extends CommandBase implements Maps{
	
		
	public MoveClimberUp() {
		requires(climber);
	}

	protected void initialize() {
		climber.set(-1.0);
	}

	protected void execute() { }

	protected boolean isFinished() {
		return climber.hasReachedUpperClimbBound();
	}

	protected void end() {
		climber.stop();
	}

	protected void interrupted() {
		climber.stop();
	}
}
