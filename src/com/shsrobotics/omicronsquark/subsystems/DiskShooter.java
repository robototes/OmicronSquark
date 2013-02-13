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
	
	private Encoder encoderWheel1 = new Encoder(Robot.Scorer.encoder1A, Robot.Scorer.encoder1B);
	private Encoder encoderWheel2 = new Encoder(Robot.Scorer.encoder2A, Robot.Scorer.encoder2B);
	
	private DigitalInput frisbeeCounter = new DigitalInput(Robot.Scorer.frisbeeCounterSwitch);
	private DigitalInput otherLimitSwitch = new DigitalInput(Robot.Scorer.shooterLimitSwitch);
    
	public DiskShooter() {
		super(Robot.Scorer.P, Robot.Scorer.I, Robot.Scorer.D);
	}
	
    public void idle(boolean state) {
        double motorValue = state ? Constants.idlePercent / 100 : 0.0;
        flywheelMotorFront.set(motorValue);
	    flywheelMotorRear.set(motorValue);
    }
    
    public void set(double value) {
        flywheelMotorFront.set(value);
		flywheelMotorRear.set(value);
    }

    public void initDefaultCommand() { }

	protected double returnPIDInput() {
		return 0.0;
	}
	public void stop() {
		flywheelMotorFront.stopMotor();
		flywheelMotorRear.stopMotor();
    }

    public void shoot() {
		diskLoader.set(1.0);
    }

	protected void usePIDOutput(double output) {
	}
}
