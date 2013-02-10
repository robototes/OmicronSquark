package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpinToGoal extends CommandBase implements Maps {
    
    private boolean aligned;
    private boolean isRotating;
    private double direction;
    
    public SpinToGoal() {
        requires(camera);
        requires(driveTrain);
    }

    protected void initialize() {
		System.out.println("SpinToGoal");
        aligned = false;
        isRotating = false;
        boolean spinRight = (driveTrain.getGyroAngle() % 180) < 0.0;
        if (spinRight) {
            direction = Constants.spinRight;
        } else {
            direction = Constants.spinLeft;
        }
}

    protected void execute() {
		if (!isRotating && goalInView()) {
            aligned = true;
        } else if (!isRotating) {
            rotateStep();
        } else if (driveTrain.onTarget()) {
			driveTrain.stop();
			Timer.delay(Constants.momentumDelay);
			isRotating = false;
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
        return camera.goalInView();
    }
    
    public void rotateStep() {       
		driveTrain.reset();
		driveTrain.rotateTo(direction * Constants.rotationStep);
		isRotating = true;		
    }
}
