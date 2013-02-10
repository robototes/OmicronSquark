package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.Timer;

public class SpinToGoal extends CommandBase implements Maps {
    
    private boolean aligned;
    private double direction;
    
    public SpinToGoal() {
        requires(camera);
        requires(driveTrain);
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
        System.out.println("setting robot to " + Constants.rotationStep * direction);	
		driveTrain.reset();
		driveTrain.rotateTo(Constants.rotationStep * direction);
		while (!driveTrain.onTarget()) {
			System.out.println("WAITING TO TURN");
		}
		driveTrain.stop();
    }
}
