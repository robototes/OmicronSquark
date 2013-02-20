package com.shsrobotics.omicronsquark.commands;

public class MoveClimberUp extends CommandBase {
	
		
	public MoveClimberUp() {
		requires(climber);
	}

	protected void initialize() {
		climber.set(-1.0);
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
