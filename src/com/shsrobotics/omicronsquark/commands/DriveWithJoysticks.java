package com.shsrobotics.omicronsquark.commands;

import edu.wpi.first.wpilibj.DriverStation;

public class DriveWithJoysticks extends CommandBase {
    
    public DriveWithJoysticks() {
       requires(driveTrain);
    }

    protected void initialize() { }

    protected void execute() {
        driveTrain.drive(oi.getX(), oi.getY(), oi.getZ());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() { }

    protected void interrupted() {
        driveTrain.stop();
    }
}
