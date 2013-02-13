package com.shsrobotics.omicronsquark.commands;

public class Shoot extends CommandBase {
	
    public Shoot() {
		requires(diskShooter);
    }

    protected void initialize() { }

    protected void execute() { }

    protected boolean isFinished() {
		return false;
    }

    protected void end() { }

    protected void interrupted() { }
}
