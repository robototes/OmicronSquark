package com.shsrobotics.omicronsquark.buttons;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class InvertedJoystickButton extends Button {

	Joystick joystick;
	int button;
	
	public InvertedJoystickButton(Joystick joystick, int button) {
		this.joystick = joystick;
		this.button = button;
	}
	
	public boolean get() {
		return !joystick.getRawButton(button);
	}
	
}
