package com.shsrobotics.omicronsquark.buttons;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Button;

public class DigitalInputButton extends Button {
	
	private DigitalInput button;

	public DigitalInputButton(int port) {
		button = new DigitalInput(port);
	}
	
	public boolean get() {
		return button.get();
	}
	
	
}
