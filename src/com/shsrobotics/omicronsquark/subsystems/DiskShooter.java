package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.ControlShooterWheelsWithThrottle;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DiskShooter extends Subsystem implements Maps {
    
    private Jaguar flywheelMotorFront = new Jaguar(Robot.Scorer.flywheelFront);
	private Jaguar flywheelMotorRear = new Jaguar(Robot.Scorer.flywheelRear);
	private Relay diskLoader = new Relay(Robot.Scorer.loader);
	
	DigitalInput loaderRegulator = new DigitalInput(Robot.Scorer.loaderRegulatorSwitch);
	
	double currentValue = 0.0;
    
	
    public void set(double value) {
		currentValue = value;
        flywheelMotorFront.set(value);
        flywheelMotorRear.set(Constants.rearMotorScaling * value);
    }
	
    public void stop() {
		flywheelMotorFront.set(0.0);
		flywheelMotorRear.set(0.0);
		diskLoader.set(OFF);
    }
	
	public void increment(double input) {
		currentValue += input;
		set(currentValue);
	}

    public void shoot() {
		set(currentValue);
		diskLoader.set(ON);
    } 
	
	public void load() {
		diskLoader.set(ON);
	}
	
	public boolean get() {
		return loaderRegulator.get();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ControlShooterWheelsWithThrottle());
	}
}
