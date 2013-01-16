package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.OI;
import com.shsrobotics.omicronsquark.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {

    public static OI oi;

    public static Camera camera = new Camera();
    public static DriveTrain driveTrain = new DriveTrain();

    public static void init() {
        oi = new OI(); // REQUIRED
        driveTrain.rotateTo(2);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
