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

    private DoubleSolenoid pistonLifter = new DoubleSolenoid(Robot.Climber.pistonLifterA, 
	    Robot.Climber.pistonLifterB);
    private DigitalInput upperClimbFlag = new DigitalInput(Robot.Climber.limitSwitchUpperClimb);
    private DigitalInput lowerClimbFlag = new DigitalInput(Robot.Climber.limitSwitchLowerClimb);
    private Talon leftTalon = new Talon(Robot.Climber.leftTalon);
    private Talon rightTalon = new Talon(Robot.Climber.rightTalon);
    
    public boolean hasReachedUpperClimbBound() {
        return upperClimbFlag.get();
    }

    public boolean hasReachedLowerClimbBound() {
        return lowerClimbFlag.get();
    }
	
	public boolean get() {
		return (pistonLifter.get().equals(EXTENDED));
	}
	
	public void extend() {
		pistonLifter.set(EXTENDED);
	}
	
	public void retract() {
		pistonLifter.set(RETRACTED);
	}
    
    public void initDefaultCommand() { }
}
