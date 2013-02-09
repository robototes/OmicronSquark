package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AlignToShoot extends CommandGroup implements Maps {

    public AlignToShoot() {
        addSequential(new SpinToGoal());
        addSequential(new FineTuneAlignment());
    }    
}
