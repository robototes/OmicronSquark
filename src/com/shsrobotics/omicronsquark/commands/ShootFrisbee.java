package com.shsrobotics.omicronsquark.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootFrisbee extends CommandGroup {
	
	public ShootFrisbee() {
		addSequential(new SetShooterWheelSpeed(0.85));
		addSequential(new WaitCommand(2.0));
		addSequential(new Shoot());
	}
}
