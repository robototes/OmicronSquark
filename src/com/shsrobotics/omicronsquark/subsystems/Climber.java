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
    private Talon leftTalon = new Talon(Robot.Climber.leftTalon);
    private Talon rightTalon = new Talon(Robot.Climber.rightTalon);
	
//	private Encoder encoder = new Encoder(Robot.Climber.encoderA, Robot.Climber.encoderB);
	
	DoubleSolenoid.Value state = RETRACTED;
	
	public Climber() {
		super();
//		encoder.start();
	}
    
    public boolean hasReachedUpperClimbBound() {
//		System.out.println(encoder.get());
//      return (encoder.get() > Robot.Climber.maxEncoderClicks);
		return false;
    }

    public boolean hasReachedLowerClimbBound() {
//		System.out.println(encoder.get());
//		return (encoder.get() < 10);
		return false;
    }
	
	public boolean get() {
		return (state.equals(EXTENDED));
	}
	
	public void extend() {
		state = EXTENDED;
		pistonLifter.set(EXTENDED);
	}
	
	public void retract() {
		state = RETRACTED;
		pistonLifter.set(RETRACTED);
	}
	
	public void set(double value) {
		leftTalon.set(value);
		rightTalon.set(value);
	}
	
	public void stop() {
		leftTalon.set(0.0);
		rightTalon.set(0.0);
	}
    
    public void initDefaultCommand() { }
}
