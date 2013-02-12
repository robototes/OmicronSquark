package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.CommandBase;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbToLevel extends CommandGroup implements Maps {
	
	public ClimbToLevel(int level) {
		for (int i = 0; i < level; i++) {
			addSequential(new MoveClimberUp());
			addSequential(new MoveClimberDown());
		}
	}
	
	public void initialize() {
	}
	
	public void end() {
	}
}
