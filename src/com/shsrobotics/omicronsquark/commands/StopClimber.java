package com.shsrobotics.omicronsquark.commands;

public class StopClimber extends CommandBase {
	
	public StopClimber() {
		requires(climber);
	}

	protected void initialize() {
		climber.stop();
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}
