package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class LockToYDirection extends CommandBase implements Maps {
	
    public LockToYDirection() {
	requires(driveTrain);
    }

    protected void initialize() { }

    protected void execute() {
	driveTrain.driveRearWheels(oi.getY());
    }

    protected boolean isFinished() {
	return false;
    }

    protected void end() { }

    protected void interrupted() {
	driveTrain.stop();
    }
}
