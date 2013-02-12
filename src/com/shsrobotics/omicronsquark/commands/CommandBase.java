package com.shsrobotics.omicronsquark.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.shsrobotics.omicronsquark.*;
import com.shsrobotics.omicronsquark.subsystems.*;

public abstract class CommandBase extends Command {

	public static OI oi;

	public static DriveTrain driveTrain = new DriveTrain();
	public static Climber climber = new Climber();
	
	public static void init() {
		oi = new OI(); // REQUIRED
		SmartDashboard.putData(driveTrain);
	}

	public CommandBase(String name) {
		super(name);
	}

	public CommandBase() {
		super();
	}
}
