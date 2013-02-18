package com.shsrobotics.omicronsquark.buttons;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class DoubleJoystickButton extends Button {

	Joystick joystickD;
	Joystick joystickS;
	int buttonD;
	int buttonS;
	
	public DoubleJoystickButton(Joystick joystickD, Joystick joystickS, int buttonD, int buttonS) {
		this.joystickD = joystickD;
		this.joystickS = joystickS;
		this.buttonD = buttonD;
		this.buttonS = buttonS;
	}
	
	public boolean get() {
		return (!joystickS.getRawButton(buttonS) || joystickD.getRawButton(buttonD));
	}
	
}
