package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GetAlignmentAngle extends CommandBase implements Maps {
    
    boolean isDone = false;
    
    public GetAlignmentAngle() {
        requires(camera);
        requires(driveTrain);
    }

    protected void initialize() {
        double angle = camera.getAlignmentAngle();
        SmartDashboard.putNumber("Horizontal Angle From Goal", angle);
    }

    protected void execute() { }

    protected boolean isFinished() {
        return true;
    }

    protected void end() { }

    protected void interrupted() {
        driveTrain.stop();
    }
}