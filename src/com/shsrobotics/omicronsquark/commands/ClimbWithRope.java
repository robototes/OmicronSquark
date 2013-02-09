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
                driveTrain.lockSolenoid();
                climbDirection = -1;
                currentPyramidLevel++;
            }
            else if ( driveTrain.hasReachedLowerClimbBound() ) {
                driveTrain.unlockSolenoid();
                climbDirection = 1;
            }
        }
        else {
            driveTrain.lockSolenoid();
            climbDirection = 0;
        }
        
        driveTrain.drive(0, climbDirection, 0);
    }
    
    protected boolean isFinished() {
        return ( currentPyramidLevel > 2 ) && driveTrain.isLocked();
    }
    
    protected void end() {
        driveTrain.drive(0,0,0);
    }
    
    protected void interrupted() {
        driveTrain.drive(0, 0, 0);
        
        //inflateAirCushion();
    }
}
