package com.shsrobotics.omicronsquark.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GetRidOfOneFrisbee extends CommandGroup {
	
	public GetRidOfOneFrisbee() {
		addSequential(new SetShooterWheelSpeed(0.1));
		addSequential(new Shoot());
	}
}
