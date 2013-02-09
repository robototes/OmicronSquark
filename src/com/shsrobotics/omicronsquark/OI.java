package com.shsrobotics.omicronsquark;

import com.shsrobotics.omicronsquark.commands.ClimbToLevel;

public class OI implements Maps {

	public OI() {
		Buttons.climb10.whenPressed(new ClimbToLevel(Constants.firstLevel));
		Buttons.climb20.whenPressed(new ClimbToLevel(Constants.secondLevel));
		Buttons.climb30.whenPressed(new ClimbToLevel(Constants.thirdLevel));
	}
	
	public double getX() {
		return joystick.getX() * getScale();
	}
	public double getY() {
		 return joystick.getY() * getScale();
	}
	public double getZ() {
		  return joystick.getZ() * getScale();
	}
	public double getScale() {
		  return joystick.getRawButton(Buttons.scaleDriveCoordinates) ? Robot.Drive.normalScale : Robot.Drive.driveCoordinateScale;
	}
}

