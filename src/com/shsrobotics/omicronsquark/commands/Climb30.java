package com.shsrobotics.omicronsquark.commands;

public class Climb30 extends CommandBase {
    
    private int climbDirection = 0;
    private int currentPyramidLevel = 0;
    
    public Climb30() {
        requires(climber);
        setInterruptible(false);
    }

    protected void initialize() { }
    
    protected void execute() {
        setClimb();
    }
    
    private void setClimb() {
        if (currentPyramidLevel < 3) {
            if (climber.hasReachedUpperClimbBound()) {
                climbDirection = -1;
                currentPyramidLevel++;
            } else if (climber.hasReachedLowerClimbBound()) {
                climbDirection = 1;
            }
        } else {
            climbDirection = 0;
        }
        
        driveTrain.drive(0, climbDirection, 0);
    }
    
    protected boolean isFinished() {
        return (currentPyramidLevel > 2);
    }
    
    protected void end() {
        driveTrain.stop();
    }
    
    protected void interrupted() {
        driveTrain.stop();
    }
}
