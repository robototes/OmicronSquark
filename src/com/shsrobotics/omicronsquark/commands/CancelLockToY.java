
package com.shsrobotics.omicronsquark.commands;

public class CancelLockToY extends CommandBase {
	
	protected void initialize() {
		new AlignToShoot().cancel();
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}
