
package com.shsrobotics.omicronsquark.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class StateMachine extends CommandGroup implements Maps {

	public StateMachine(int placement, int scoringOptions) { // make to-do list
		if (scoringOptions != Constants.doNotScore) { // put flying discs into 1-point goal
			if (scoringOptions == Constants.fiveSecondDelay) {
				addSequential(new WaitCommand(5.0)); // wait 5 seconds before starting
			}
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
		} else { // if both other teams are using 1-point goal and take a long time

		}
	}
}
