package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DiskShooter extends Subsystem implements Maps {
    
    private Jaguar flywheelMotorFront = new Jaguar(Robot.Scorer.flywheelFront);
	private Jaguar flywheelMotorRear = new Jaguar(Robot.Scorer.flywheelRear);
	private Talon diskLoader = new Talon(Robot.Scorer.loader);

	public static final double standardBatteryVoltage = 12.16;  // this number is made up, fix
	
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
		stopWheels();
		stopLoader();
    }
	
	public void stopWheels() {
		flywheelMotorFront.set(0.0);
		flywheelMotorRear.set(0.0);
	}
	
	public void increment(double input) {
		currentValue += input;
		set(currentValue);
	}

   	
	public void loadAutonomous() {
		diskLoader.set(1.0);
	}
	
	public void loadReverse() {
		diskLoader.set(-1.0);
	}
	
	public void stopLoader() {
		diskLoader.set(0.0);
	}
	
	public void loadTeleoperated() {
		diskLoader.set(1.0);
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
