package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.OI;
import com.shsrobotics.omicronsquark.subsystems.*;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {

    public static OI oi;
	
	public static DiskShooter diskShooter = new DiskShooter();	
	public static DriveTrain driveTrain = new DriveTrain();
	
    public static void init() {
        oi = new OI(); // REQUIRED
        driveTrain.reset();
		Watchdog.getInstance().setEnabled(false);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
