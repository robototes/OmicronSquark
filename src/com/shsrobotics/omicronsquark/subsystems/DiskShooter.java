package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DiskShooter extends PIDSubsystem implements Maps {
    
    private Jaguar flywheelMotorFront = new Jaguar(Robot.Scorer.flywheelFront);
	private Jaguar flywheelMotorRear = new Jaguar(Robot.Scorer.flywheelRear);
	private Jaguar diskLoader = new Jaguar(Robot.Scorer.loader);
	
	private Encoder encoderWheelFront = new Encoder(Robot.Scorer.encoderFrontA, Robot.Scorer.encoderFrontB);
	private Encoder encoderWheelRear = new Encoder(Robot.Scorer.encoderRearA, Robot.Scorer.encoderRearB);
	
	private DigitalInput frisbeeCounter = new DigitalInput(Robot.Scorer.frisbeeCounterSwitch);
	private DigitalInput otherLimitSwitch = new DigitalInput(Robot.Scorer.shooterLimitSwitch);
    
	public DiskShooter() {
		super(Robot.Scorer.P, Robot.Scorer.I, Robot.Scorer.D);
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
    }

    public void shoot() {
		diskLoader.set(1.0);
    }   

	protected double returnPIDInput() {
		return 0.0;
	}

	protected void usePIDOutput(double output) {
	}
	
	public void initDefaultCommand() { }
}
