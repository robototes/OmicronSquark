package com.shsrobotics.omicronsquark.commands;

public class MoveClimberDown extends CommandBase {
	
	private boolean limitSwitch;
	private boolean allowEnd;
	private boolean done;
	
	public MoveClimberDown() {
		requires(driveTrain);
		setInterruptible(false);
	}

	protected void initialize() {
		allowEnd = false;
		done = false;
	}

	protected void execute() {
		limitSwitch = driveTrain.hasReachedUpperClimbBound();
		if (limitSwitch) {
			allowEnd = true;			
		} else if (allowEnd) {
			done = true;
		}
		driveTrain.drive(0.0, -1.0, 0.0);
	}

	protected boolean isFinished() {
		return done;
	}

	protected void end() { }

	protected void interrupted() { }
}
