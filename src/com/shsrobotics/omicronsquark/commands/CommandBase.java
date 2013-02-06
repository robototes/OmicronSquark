package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.OI;
import com.shsrobotics.omicronsquark.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class CommandBase extends Command {

    public static OI oi;

    public static Camera camera = new Camera();
    public static DriveTrain driveTrain = new DriveTrain();

    public static void init() {
        oi = new OI(); // REQUIRED
        SmartDashboard.putData(driveTrain);
        SmartDashboard.putData("PID", driveTrain.getPIDController());
        driveTrain.reset();
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
