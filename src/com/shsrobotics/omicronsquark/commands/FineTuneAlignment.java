package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class FineTuneAlignment extends CommandBase implements Maps {
    
    private boolean error;
    
    public FineTuneAlignment() {
        requires(camera);
        requires(driveTrain);
        setRunWhenDisabled(false);
        setInterruptible(false);
    }

    protected void initialize() {
        double angle = camera.getAlignmentAngle();
        if (angle != Double.NEGATIVE_INFINITY) {
            driveTrain.rotateTo(angle - driveTrain.getGyroAngle());
        } else {
            error = true;
        }
    }

    protected void execute() { }

    protected boolean isFinished() {
        return driveTrain.onTarget() || error;
    }

    protected void end() { }

    protected void interrupted() {
        driveTrain.stop();
    }
}
