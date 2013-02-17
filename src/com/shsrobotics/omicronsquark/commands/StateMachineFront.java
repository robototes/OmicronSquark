package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class StateMachineFront extends CommandGroup implements Maps {
    
    public StateMachineFront() {
        
		addSequential(new AlignToShoot());
		addSequential(new Shoot());
		addSequential(new Shoot());
    }
    
}
