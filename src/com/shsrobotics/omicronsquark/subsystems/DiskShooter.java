package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DiskShooter extends Subsystem implements Maps {
    
    private Jaguar frontFlywheelMotor = new Jaguar(Robot.Scorer.flywheelFront);
    private Jaguar rearFlywheelMotor = new Jaguar(Robot.Scorer.flywheelRear);
    private Jaguar diskLoader = new Jaguar(Robot.Scorer.loader);
    
    public void idle(boolean state) {
        double motorValue;
	if (state) {
	    motorValue = Constants.idlePercent / 100;
	} else {
	    motorValue = 0.0;
	}
	
        frontFlywheelMotor.set(motorValue);
        rearFlywheelMotor.set(motorValue);
    }
    
    public void set(double value) {
        frontFlywheelMotor.set(value);
        rearFlywheelMotor.set(value);
    }
	
    public void stop() {
	frontFlywheelMotor.stopMotor();
	rearFlywheelMotor.stopMotor();
    }

    public void shoot() {
	diskLoader.set(1.0);
    }

    public void initDefaultCommand() { }
}
