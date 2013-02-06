package com.shsrobotics.omicronsquark.commands;

import edu.wpi.first.wpilibj.DriverStation;


public class StayAtConstantAngularDisplacement extends CommandBase {
    
    public StayAtConstantAngularDisplacement() {
        requires(driveTrain);
        setRunWhenDisabled(false);
    }
    
    protected void initialize() {
        driveTrain.rotateTo(0.5);
    }

    protected void execute() { }        

    protected boolean isFinished() {
        return DriverStation.getInstance().isDisabled();
    }
    
    protected void end() { }
    
    protected void interrupted() {
        driveTrain.stop();
    }
}
