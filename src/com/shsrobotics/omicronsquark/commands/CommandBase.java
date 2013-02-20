package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.OI;
import com.shsrobotics.omicronsquark.subsystems.*;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class CommandBase extends Command {

    public static OI oi;
	
    public static Camera camera = new Camera();
    public static DriveTrain driveTrain = new DriveTrain();
	public static DiskShooter diskShooter = new DiskShooter();	
	public static Climber climber = new Climber();
	public static AirCompressor compressor = new AirCompressor();
	
    public static void init() {
        oi = new OI(); // REQUIRED
        SmartDashboard.putData(driveTrain);
        SmartDashboard.putData(camera);
		SmartDashboard.putData(diskShooter);
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
