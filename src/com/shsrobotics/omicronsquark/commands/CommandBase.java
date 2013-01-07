package com.shsrobotics.omicronsquark.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.shsrobotics.omicronsquark.OI;
import com.shsrobotics.omicronsquark.subsystems.DriveTrain;

public abstract class CommandBase extends Command {

	public static OI oi;

	public static DriveTrain driveTrain = new DriveTrain();

	public static void init() {
		oi = new OI(); // REQUIRED
	}

	public CommandBase(String name) {
		super(name);
	}

	public CommandBase() {
		super();
	}
}
