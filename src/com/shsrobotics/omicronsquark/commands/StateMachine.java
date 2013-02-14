
package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class StateMachine extends CommandGroup implements Maps {

	public StateMachine() { // make to-do list<<<<<<< HEAD
		addSequential(new DriveForTime(1, 0, 0, 1.0)); // drive right for 1 second
		addSequential(new DriveForTime(0, 1, 0, 1.0)); // drive forwards for 1 second
		addSequential(new AlignToShoot());
		addSequential(new Shoot());
		addSequential(new Shoot());
	}
}
