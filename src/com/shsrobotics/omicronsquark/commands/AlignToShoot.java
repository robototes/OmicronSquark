package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AlignToShoot extends CommandGroup implements Maps {

    public AlignToShoot() {
        addSequential(new SpinToGoal());
        addSequential(new WaitCommand(Constants.momentumDelay));
		addParallel(new SetShooterWheelSpeed(0.85));
        addSequential(new FineTuneAlignment());
		addSequential(new WaitCommand(0.5));
		addSequential(new Shoot(), 2.5); // shoot, and end after four seconds.
    }   
}
