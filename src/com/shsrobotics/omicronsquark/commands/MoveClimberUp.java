package com.shsrobotics.omicronsquark.commands;

public class MoveClimberUp extends CommandBase {
	
	private boolean limitSwitch;
	private boolean allowEnd;
	private boolean done;
	
	public MoveClimberUp() {
		requires(climber);
		setInterruptible(false);
	}

	protected void initialize() {
		allowEnd = false;
		done = false;
		climber.extend();
	}

	protected void execute() {
		limitSwitch = climber.hasReachedUpperClimbBound();
		if (limitSwitch) {
			allowEnd = true;			
		} else if (allowEnd) {
			done = true;
		}
	}

	protected boolean isFinished() {
		return done;
	}

	protected void end() { }

	protected void interrupted() { }
}
