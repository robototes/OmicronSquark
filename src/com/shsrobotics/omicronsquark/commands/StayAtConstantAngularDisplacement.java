package com.shsrobotics.omicronsquark.commands;


public class StayAtConstantAngularDisplacement extends CommandBase {
    
    public StayAtConstantAngularDisplacement() {
        requires(driveTrain);
        setRunWhenDisabled(false);
    }
    
    protected void initialize() {
    }

    protected void execute() {
       driveTrain.rotateTo(0);
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() { }
    
    protected void interrupted() {
        driveTrain.stop();
    }
}
