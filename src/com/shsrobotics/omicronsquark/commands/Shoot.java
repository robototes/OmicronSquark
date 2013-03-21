package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.Timer;

public class Shoot extends CommandBase implements Maps {

	private boolean spinUpWheels;
	
    public Shoot() {
		requires(diskShooter);
		setTimeout(3.0);
    }

    protected void initialize() {
		if (Global.currentDriverStationMode == Constants.shootMode) {
			if (diskShooter.getValue() == 0.0) {
				spinUpWheels = true;
			} else {
				spinUpWheels = false;
			}
			diskShooter.set((1 - oi.getThrottle()) / 2);
			if (spinUpWheels) {
				Timer.delay(Constants.speedUpDelay * diskShooter.getValue());
			}
			diskShooter.shoot();
		} else {
			end();
		}
	}

    protected void execute() { }

    protected boolean isFinished() {
		return isTimedOut();
    }

    protected void end() {
		diskShooter.stop();
    }

    protected void interrupted() {
		diskShooter.stop();
    }
}