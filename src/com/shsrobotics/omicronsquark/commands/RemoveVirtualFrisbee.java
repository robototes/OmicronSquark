package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;

public class RemoveVirtualFrisbee extends CommandBase {

	protected void initialize() {
		Global.currentFrisbeeCount--;
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }	
}
