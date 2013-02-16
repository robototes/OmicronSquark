package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DiskShooter extends PIDSubsystem implements Maps {
    
    private Jaguar flywheelMotorFront = new Jaguar(Robot.Scorer.flywheelFront);
	private Jaguar flywheelMotorRear = new Jaguar(Robot.Scorer.flywheelRear);
	private Relay diskLoader = new Relay(Robot.Scorer.loader);
	
	private Encoder encoderWheelFront = new Encoder(Robot.Scorer.encoderFrontA, Robot.Scorer.encoderFrontB);
	private Encoder encoderWheelRear = new Encoder(Robot.Scorer.encoderRearA, Robot.Scorer.encoderRearB);
	
	DigitalInput loaderRegulator = new DigitalInput(Robot.Scorer.loaderRegulatorSwitch);
	
	double currentValue = 0.0;
    
	public DiskShooter() {
		super(Robot.Scorer.P, Robot.Scorer.I, Robot.Scorer.D);
		setInputRange(0.0, 300000.0);  // That's 300,000
		encoderWheelFront.setDistancePerPulse(1 / Robot.Scorer.encoderPulsesPerRevolution);
		encoderWheelRear.setDistancePerPulse(1 / Robot.Scorer.encoderPulsesPerRevolution);
	}
	
    public void idle(boolean state) {
        double motorValue;
		if (state) {
			motorValue = Constants.idlePercent / 100;
		} else {
			motorValue = 0.0;
		}
	
        flywheelMotorFront.set(motorValue);
        flywheelMotorRear.set(motorValue);
    }
    
    public void set(double value) {
        flywheelMotorFront.set(value);
        flywheelMotorRear.set(value);
    }
	
    public void stop() {
		flywheelMotorFront.stopMotor();
		flywheelMotorRear.stopMotor();
		diskLoader.set(OFF);
    }
	
	public void increment(double input) {
		currentValue = flywheelMotorFront.get() + input;
		flywheelMotorFront.set(currentValue);
		flywheelMotorRear.set(currentValue);
	}

    public void shoot() {
		flywheelMotorFront.set(currentValue);
		flywheelMotorRear.set(currentValue);
		diskLoader.set(ON);
		SmartDashboard.putBoolean("diskLoader", (diskLoader.get() == ON));
    } 
	
	public boolean get() {
		return loaderRegulator.get();
	}

	protected double returnPIDInput() {
		double frontWheelVelocity = encoderWheelFront.getRate();
		double rearWheelVelocity = encoderWheelRear.getRate() * Constants.frontToRearMotorSpeedRatio;
		return (frontWheelVelocity + rearWheelVelocity) / 2;
	}

	protected void usePIDOutput(double output) {
		flywheelMotorFront.set(output);
		flywheelMotorRear.set(output / Constants.frontToRearMotorSpeedRatio);
	}
	
	public void initDefaultCommand() { }
}
