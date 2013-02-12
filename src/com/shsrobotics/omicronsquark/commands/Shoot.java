package com.shsrobotics.omicronsquark.commands;

public class Shoot extends CommandBase {
	
    public Shoot() {
	requires(diskShooter);
    }

    protected void initialize() { }

    protected void execute() {
	diskShooter.set(0.9);
	diskShooter.shoot();
    }

    protected boolean isFinished() {
	return false;
    }

    protected void end() {
	diskShooter.stop();
    }

    protected void interrupted() {
	diskShooter.stop();
    }
}
