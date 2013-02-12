/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem implements Maps {

    private DoubleSolenoid driveTrainLockPiston = new DoubleSolenoid(Maps.Robot.Climber.lockDriveTrainPistonA,
	    Maps.Robot.Climber.lockDriveTrainPistonB);
    private DigitalInput upperClimbFlag = new DigitalInput(Maps.Robot.Climber.limitSwitchUpperClimb);
    private DigitalInput lowerClimbFlag = new DigitalInput(Maps.Robot.Climber.limitSwitchLowerClimb);
    private Talon leftTalon = new Talon(Robot.Climber.leftTalon);
    private Talon rightTalon = new Talon(Robot.Climber.rightTalon);
    
    public boolean hasReachedUpperClimbBound() {
        return upperClimbFlag.get();
    }
    
    /**
     * @return Weather the robot has completely extended its climbing arm.
     */
    public boolean hasReachedLowerClimbBound() {
        return lowerClimbFlag.get();
    }
    
    public void initDefaultCommand() { }
}
