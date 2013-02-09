
package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.*;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem implements Maps {
    
    private RobotDrive robotDrive = new RobotDrive(Robot.Drive.frontLeftWheel, Robot.Drive.rearLeftWheel, Robot.Drive.frontRightWheel, Robot.Drive.rearRightWheel);
    private Encoder encoder = new Encoder(Robot.Drive.encoderASource, Robot.Drive.encoderBSource, Robot.Drive.encoderReverseDirection);
    private Solenoid climbLock = new Solenoid(Robot.Drive.solenoidLockClimb);
    private DigitalInput upperClimbFlag = new DigitalInput(Robot.Drive.limitSwitchUpperClimb);
    private DigitalInput lowerClimbFlag = new DigitalInput(Robot.Drive.limitSwitchLowerClimb);
    
    
    public DriveTrain() {
        
    }

    public void stop() {
        robotDrive.stopMotor();
    }
    
    public void drive(double x, double y, double z) {
            x = MathUtils.pow(x, 3);
            y = MathUtils.pow(y, 3);
            z = MathUtils.pow(z, 3);
            robotDrive.mecanumDrive_Cartesian(x, y, z, 0.0);
    }
    
    /**
     * @return Weather the robot has pulled itself up to the next level.
     */
    public boolean hasReachedUpperClimbBound() {
        return upperClimbFlag.get();
    }
    
    /**
     * @return Weather the robot has completely extended its climbing arm.
     */
    public boolean hasReachedLowerClimbBound() {
        return lowerClimbFlag.get();
    }
    
    /**
     * Causes the locking solenoid to lock the robot to the pyramid frame.
     */
    public void lockSolenoid() {
        climbLock.set(true);
    }
    
    /**
     * Causes the locking solenoid to unlock from the pyramid.
     */
    public void unlockSolenoid() {
        climbLock.set(false);
    }
    
    public void initDefaultCommand() {
            setDefaultCommand(new DriveWithJoysticks());
    }
    
    /*
     * @return Wheather the locking solenoid has been locked.
     */
    public boolean isLocked() {
        return climbLock.get();
    }
}