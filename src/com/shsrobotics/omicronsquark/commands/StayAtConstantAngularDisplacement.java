package com.shsrobotics.omicronsquark.commands;


public class StayAtConstantAngularDisplacement extends CommandBase {
    
    public StayAtConstantAngularDisplacement() {
        requires(driveTrain);
    }
    
    protected void initialize() {
       driveTrain.rotateTo(0);
    }

    protected void execute() { }
        

    protected boolean isFinished() {
        return false;
    }

    protected void end() { }

    protected void interrupted() {
        driveTrain.stop();
    }
}
