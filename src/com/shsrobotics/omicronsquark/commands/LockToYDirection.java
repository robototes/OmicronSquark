package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
import com.shsrobotics.omicronsquark.Maps;

public class LockToYDirection extends CommandBase implements Maps {

    public LockToYDirection() {
		requires(driveTrain);
    }
	
	protected void initialize() {
		driveTrain.rotateTo(driveTrain.getGyroAngle());
	}

    protected void execute() {
		driveTrain.setUserAlignment(oi.getY());
    }

    protected boolean isFinished() {
		return false;
    }

	protected void end() { }


    protected void interrupted() {
		driveTrain.stop();
    }
}
