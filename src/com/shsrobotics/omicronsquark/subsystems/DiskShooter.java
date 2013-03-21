package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Global;
import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DiskShooter extends Subsystem implements Maps {
    
    private Jaguar flywheelMotorFront = new Jaguar(Robot.Scorer.flywheelFront);
	private Jaguar flywheelMotorRear = new Jaguar(Robot.Scorer.flywheelRear);
	private Relay diskLoader = new Relay(Robot.Scorer.loader);

	public static final double standardBatteryVoltage = 12.16;  // this number is made up, fix

	DigitalInput loaderRegulator = new DigitalInput(Robot.Scorer.loaderRegulatorSwitch);
	
	double currentValue = 0.0;
	
    public void set(double value) {
		currentValue = value;
		double compensated = compensateBatteryVoltage() * value;
        flywheelMotorFront.set(compensated);
        flywheelMotorRear.set(Constants.rearMotorScaling * compensated);
		SmartDashboard.putNumber("Flywheel Value", value);
    }
	
	public void setRaw(double value) {
		currentValue = value;
		flywheelMotorFront.set(value);
		flywheelMotorRear.set(Constants.rearMotorScaling * value);
		SmartDashboard.putNumber("Flywheel Value", value);
	}
	
    public void stop() {
		set(0.0);
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
	
	public void setLoader(Relay.Value value) {
		diskLoader.set(value);
	}
	
	public boolean get() {
		return !loaderRegulator.get();
	}
	
	public double getValue() {
		return currentValue;
	}

	public double compensateBatteryVoltage() {
		double compensatedVoltage;
		double voltage1 = DriverStation.getInstance().getBatteryVoltage();
		double voltage2 = DriverStation.getInstance().getBatteryVoltage();
		
		double currentVoltage = (voltage1 + voltage2) / 2;
		compensatedVoltage = standardBatteryVoltage / currentVoltage;
		
		return compensatedVoltage;
	}
	
	public void initDefaultCommand() { }
}
