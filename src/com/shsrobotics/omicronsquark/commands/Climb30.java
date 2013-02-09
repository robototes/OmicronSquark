package com.shsrobotics.omicronsquark.commands;

public class Climb30 extends CommandBase {
    
    private int climbDirection = 0;
    private int currentPyramidLevel = 0;
    
    public Climb30() {
        requires(driveTrain);
        setInterruptible(false);
    }

    protected void initialize() {
		driveTrain.lockDriveTrainToClimber();
	}
    
    protected void execute() {
        setClimb();
    }
    
    private void setClimb() {
        if (currentPyramidLevel < 3) {
            if (driveTrain.hasReachedUpperClimbBound()) {
                climbDirection = -1;
                currentPyramidLevel++;
            } else if (driveTrain.hasReachedLowerClimbBound()) {
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
		driveTrain.unlockDriveTrainFromClimber();
    }
    
    protected void interrupted() {
        driveTrain.stop();
        driveTrain.unlockDriveTrainFromClimber();
    }
}
