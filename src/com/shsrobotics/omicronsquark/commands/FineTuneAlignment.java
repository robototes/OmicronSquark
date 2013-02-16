package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.subsystems.Camera.Angles;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FineTuneAlignment extends CommandBase implements Maps {
    
    private boolean error;
	private double horizontalAngle;
	private double verticalAngle;
	private int executeCount;
    
    public FineTuneAlignment() {
        requires(camera);
        requires(driveTrain);
        setRunWhenDisabled(false);
        setInterruptible(false);
    }

    protected void initialize() {
		executeCount = 0;
        Angles angles = camera.getAlignmentAngles();
		horizontalAngle = angles.horizontal;
		verticalAngle = angles.vertical;
        if (horizontalAngle != Double.NEGATIVE_INFINITY) {
            System.out.println("Setting robot to " + horizontalAngle);
			driveTrain.reset();
			driveTrain.rotateTo(horizontalAngle);			
			updateRobotPosition();
        } else {
            System.out.println("Goal not found.");
            error = true;
        }
    }

    protected void execute() { 
		driveTrain.setUserAlignment(oi.getY());
		if (++executeCount % 10 == 0) { // run this code every 10 times
			verticalAngle = camera.getAlignmentAngles().vertical;
			updateRobotPosition();		
		}
	}
	
    protected boolean isFinished() {
        return driveTrain.onTarget() || error;
    }

    protected void end() {
        driveTrain.stop();
    }

    protected void interrupted() {
        driveTrain.stop();
    }

	private void updateRobotPosition() {
		if (Math.abs(verticalAngle + Constants.shooterAngleAdjustment) < Constants.significanceLevel_Angle) { // right on
			SmartDashboard.putString("Robot Position", "On Target");
		} else if (verticalAngle + Constants.shooterAngleAdjustment < 0.0) { // too far
			SmartDashboard.putString("Robot Position", "Too Far");
		} else if (verticalAngle + Constants.shooterAngleAdjustment > 0.0) { // too close
			SmartDashboard.putString("Robot Position", "Too Close");
		}
	}
}
