package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class StateMachineFront extends CommandGroup implements Maps {
    
    public StateMachineFront() {        
		addSequential(new SetShooterWheelSpeed(1.0));
		addSequential(new WaitCommand(Constants.loadingDelay));
		addSequential(new LoaderForward());
    }
    
}
