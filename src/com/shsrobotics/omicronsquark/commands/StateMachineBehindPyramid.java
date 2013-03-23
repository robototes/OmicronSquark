
package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class StateMachineBehindPyramid extends CommandGroup implements Maps {

	public StateMachineBehindPyramid() { // make to-do list
		addSequential(new SetShooterWheelSpeed(Constants.defaultShootingBehindPyramidValue));
			addSequential(new WaitCommand(Constants.speedUpDelay)); // wait for shooter to spin up
		addSequential(new LoaderForward());
	}
}
