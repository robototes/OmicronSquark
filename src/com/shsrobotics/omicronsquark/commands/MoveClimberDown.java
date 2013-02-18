package com.shsrobotics.omicronsquark.commands;

public class MoveClimberDown extends CommandBase {
	
	private boolean limitSwitch;
	private boolean allowEnd;
	private boolean done;
	
	public MoveClimberDown() {
		requires(climber);
		setInterruptible(false);
	}

	protected void initialize() {
		allowEnd = false;
		done = false;
		climber.set(-1.0);
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

	protected void end() {
		climber.stop();
	}

	protected void interrupted() {
		climber.stop();
	}
}
