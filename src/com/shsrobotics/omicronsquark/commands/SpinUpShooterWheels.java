package com.shsrobotics.omicronsquark.commands;

public class SpinUpShooterWheels extends CommandBase {

    public SpinUpShooterWheels() {
	requires(diskShooter);
    }

    protected void initialize() { }

    protected void execute() {
	diskShooter.set(0.9);
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
