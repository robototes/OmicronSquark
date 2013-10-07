package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Blocker extends Subsystem implements Maps {
	
	private Relay blockerMotor = new Relay(Robot.blockerMotor);
	
	public void raise() {
		blockerMotor.set(REVERSE);
	}
	
	public void lower() {
		blockerMotor.set(ON);
	}
	
	public void stop() {
		blockerMotor.set(OFF);
	}
	
	public void initDefaultCommand() { }
}
