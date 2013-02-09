package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class SpinToGoal extends CommandBase implements Maps {
    
    private boolean aligned;
    private double direction;
    
    public SpinToGoal() {
        requires(driveTrain);
        requires(camera);
    }

    protected void initialize() {
        aligned = false;
        boolean spinRight = (driveTrain.getGyroAngle() % 180) < 0.0;
        if (spinRight) {
            direction = Constants.spinRight;
        } else {
            direction = Constants.spinLeft;
        }
}

    protected void execute() {
        if (goalInView()) {
            aligned = true;
        } else {
            rotateStep();
        }
    }

    protected boolean isFinished() {
        return aligned;
    }

    protected void end() { }

    protected void interrupted() {
        driveTrain.stop();
    }
    
    public boolean goalInView() {
        return (camera.getAlignmentAngle() != Double.NEGATIVE_INFINITY);
    }
    
    public void rotateStep() {
        driveTrain.rotateTo(Constants.rotationStep * direction);
        while (driveTrain.onTarget() == false) { }
    }
}
