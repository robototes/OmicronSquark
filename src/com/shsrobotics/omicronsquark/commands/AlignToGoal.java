package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class AlignToGoal extends CommandBase implements Maps {
    
    public AlignToGoal() {
        requires(camera);
        requires(driveTrain);
        setRunWhenDisabled(false);
        setInterruptible(false);
    }

    protected void initialize() {
        double angle = camera.getAlignmentAngle();
        if (angle != Double.NEGATIVE_INFINITY) {
            driveTrain.rotateTo(angle);
        }
    }

    protected void execute() { }

    protected boolean isFinished() {
        return driveTrain.onTarget();
    }

    protected void end() { }

    protected void interrupted() {
        driveTrain.stop();
    }
}
