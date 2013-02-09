package com.shsrobotics.omicronsquark.commands;

/**
 *
 * @author Max
 */
public class ClimbWithRope extends CommandBase {
    
    private int climbDirection = 0;
    private int currentPyramidLevel = 0;
    
    public ClimbWithRope() {
        requires(driveTrain);
        setInterruptible(false);
    }

    protected void initialize() {
        
    }
    
    protected void execute() {
        setClimb();
    }
    
    private void setClimb() {
        if ( currentPyramidLevel < 3 ) {
            if ( driveTrain.hasReachedUpperClimbBound() ) {
                climbDirection = -1;
                currentPyramidLevel++;
            }
            else if ( driveTrain.hasReachedLowerClimbBound() ) {
                climbDirection = 1;
            }
        }
        else {
            climbDirection = 0;
        }
        
        driveTrain.drive(0, climbDirection, 0);
    }
    
    protected boolean isFinished() {
        return currentPyramidLevel > 2;
    }
    
    protected void end() {
        
    }
    
    protected void interrupted() {
        driveTrain.drive(0, 0, 0);
        
        //inflateAirCushion();
    }
}
