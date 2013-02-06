package com.shsrobotics.omicronsquark.commands;

public class ResetGyroscope extends CommandBase {
    
    public ResetGyroscope() {
        requires(driveTrain);
    }

    protected void initialize() {
        driveTrain.resetGyro();
    }

    protected void execute() { }

    protected boolean isFinished() {
        return true;
    }

    protected void end() { }

    protected void interrupted() { }
}
