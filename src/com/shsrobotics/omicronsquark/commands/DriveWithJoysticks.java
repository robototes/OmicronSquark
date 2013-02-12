package com.shsrobotics.omicronsquark.commands;

public class DriveWithJoysticks extends CommandBase {
    
    public DriveWithJoysticks() {
       requires(driveTrain);
       setRunWhenDisabled(false);
    }

    protected void initialize() { }

    protected void execute() {
        boolean fieldCentric = oi.getThrottle() < 0.0;
        driveTrain.drive(oi.getX(), oi.getY(), oi.getZ(), false);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() { }

    protected void interrupted() {
        driveTrain.stop();
    }
}
