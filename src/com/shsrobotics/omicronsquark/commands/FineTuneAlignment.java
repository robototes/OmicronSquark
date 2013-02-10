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
        System.out.println("FineTuneAlignment");
        double angle = camera.getAlignmentAngle();
        if (angle != Double.NEGATIVE_INFINITY) {
            System.out.println("setting robot to " + angle);
			driveTrain.reset();
			driveTrain.rotateTo(angle);
        } else {
            System.out.println("goal not found");
            error = true;
        }
    }

    protected void execute() { }
	
    protected boolean isFinished() {
        return driveTrain.onTarget() || error;
    }

    protected void end() {
        driveTrain.stop();
    }

    protected void interrupted() {
        driveTrain.stop();
    }
}
