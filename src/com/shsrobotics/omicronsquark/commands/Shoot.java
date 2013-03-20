package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class Shoot extends CommandBase implements Maps {

	private boolean hasLeft;
	private boolean spinUpWheels;
	
    public Shoot() {
		requires(diskShooter);
		setTimeout(3.0);
    }

    protected void initialize() {
		if (Global.currentDriverStationMode == Constants.shootMode) {
			hasLeft = !diskShooter.get();
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

    protected void execute() {
		if (!hasLeft && !diskShooter.get()) {
			hasLeft = true;
		}
	}

    protected boolean isFinished() {
		return (diskShooter.get() && hasLeft) || isTimedOut();
    }

    protected void end() {
		Global.currentFrisbeeCount--;
		diskShooter.stop();
    }

    protected void interrupted() {
		diskShooter.stop();
    }
}