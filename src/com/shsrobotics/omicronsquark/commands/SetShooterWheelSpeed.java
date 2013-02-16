package com.shsrobotics.omicronsquark.commands;

public class SetShooterWheelSpeed extends CommandBase {
	
	private final double velocity;

    public SetShooterWheelSpeed(double speed) {
		velocity = speed;
		requires(diskShooter);
    }

    protected void initialize() { }

    protected void execute() {				
		diskShooter.set(velocity);
	}

    protected boolean isFinished() {
		return false;
    }

    protected void end() { }

    protected void interrupted() { }
}
