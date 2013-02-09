package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.Timer;

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

    protected void end() {
        driveTrain.stop();
    }

    protected void interrupted() {
        driveTrain.stop();
    }
    
    public boolean goalInView() {
        System.out.println("goalInView()");
        return camera.goalInView();
    }
    
    public void rotateStep() {
        System.out.println("rotateStep()");
        System.out.println("setting robot to " + driveTrain.getGyroAngle() + Constants.rotationStep * direction);
        driveTrain.rotateTo(driveTrain.getGyroAngle() + Constants.rotationStep * direction);
        while (driveTrain.onTarget() == false) { } 
        driveTrain.stop();
        try {
            Thread.sleep((long) Constants.momentumDelay * 1000); // wait for robot to become stable
        } catch (InterruptedException ex) { }
    }
}
