package com.shsrobotics.omicronsquark.commands;

public class SetShooterWheelSpeed extends CommandBase {
	
	private final double velocity;

    public SetShooterWheelSpeed(double speed) {
		velocity = speed;
		requires(diskShooter);
    }

    protected void initialize() {					
		diskShooter.set(velocity);
	}
	
	protected void execute() { }

    protected boolean isFinished() {
		return true;
    }

    protected void end() { }

    protected void interrupted() { }
}
