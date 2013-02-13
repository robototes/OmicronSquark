package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AlignToShoot extends CommandGroup implements Maps {

    public AlignToShoot() {
        addSequential(new SpinToGoal());
        addSequential(new WaitCommand(Constants.momentumDelay));
		addParallel(new SpinUpShooterWheels());
        addSequential(new FineTuneAlignment());
		addSequential(new Shoot(), 4.0); // shoot, and end after four seconds.
    }   
}
