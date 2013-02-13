package com.shsrobotics.omicronsquark.commands;

public class SpinUpShooterWheels extends CommandBase {

    public SpinUpShooterWheels() {
		requires(diskShooter);
    }

    protected void initialize() {		
		diskShooter.set(0.9);
	}

    protected void execute() { }

    protected boolean isFinished() {
		return true;
    }

    protected void end() {
		diskShooter.stop();
    }

    protected void interrupted() {
		diskShooter.stop();
    }
}
