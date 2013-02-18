package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
import com.shsrobotics.omicronsquark.Maps;

public class Shoot extends CommandBase implements Maps {

	private boolean hasLeft;
	
    public Shoot() {
		requires(diskShooter);
		setTimeout(5.0); // five seconds maximum to shoot
    }

    protected void initialize() {
		hasLeft = !diskShooter.get();
		diskShooter.shoot();
	}

    protected void execute() {
		if (!hasLeft && !diskShooter.get()) {
			hasLeft = true;
		}
		if (driverJoystick.getRawButton(Buttons.reverseLoader)) {
			diskShooter.setLoader(REVERSE);
		} else {
			diskShooter.setLoader(ON);
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