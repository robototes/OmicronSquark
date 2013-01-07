
package com.shsrobotics.omicronsquark.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.shsrobotics.omicronsquark.Maps;

public class StateMachine extends CommandGroup implements Maps {

	public StateMachine(int placement) { // make to-do list
		switch (placement) {
			case Constants.nearRightCorner:
				break;
			case Constants.nearLeftCorner:
				break;
			case Constants.farRightCorner:
				break;
			case Constants.farLeftCorner:
				break;
			default:
				break;
		}
	}
}
