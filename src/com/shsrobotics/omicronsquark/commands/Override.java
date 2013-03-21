package com.shsrobotics.omicronsquark.commands;

import edu.wpi.first.wpilibj.command.Scheduler;

public class Override extends CommandBase {

	protected void initialize() { }

	protected void execute() {
		Scheduler.getInstance().disable();
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Scheduler.getInstance().enable();
	}

	protected void interrupted() {
		Scheduler.getInstance().enable();
	}
}
