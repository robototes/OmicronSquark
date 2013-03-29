package com.shsrobotics.omicronsquark;

import com.shsrobotics.omicronsquark.commands.*;
import com.sun.squawk.util.MathUtils;

public class OI implements Maps {

    public OI() {
		Buttons.loaderForwards.whileHeld(new LoaderForward());
			Buttons.loaderForwards.whenReleased(new LoaderOff());
		Buttons.loaderReverse.whileHeld(new LoaderReverse());			
			Buttons.loaderReverse.whenReleased(new LoaderOff());
		Buttons.bringWheelsToSpeedToDump.whileHeld(new BringWheelsToSpeedForDumping());
			Buttons.bringWheelsToSpeedToDump.whenReleased(new ZeroFlywheels());
		Buttons.bringWheelsToSpeedToShootFromSide.whileHeld(new BringWheelsToSpeedForPyramidSide());
			Buttons.bringWheelsToSpeedToShootFromSide.whenReleased(new ZeroFlywheels());
		Buttons.bringWheelsToSpeedToShootFromBack.whileHeld(new BringWheelsToSpeedForPyramidBack());
			Buttons.bringWheelsToSpeedToShootFromBack.whenReleased(new ZeroFlywheels());
		Buttons.override.whileHeld(new Override());
    }
    
    public double getX() {
        return driverJoystick.getX() * getScale();
    }
    public double getY() {
        return driverJoystick.getY() * getScale();
    }
    public double getZ() {		
        return driverJoystick.getZ() * getScale();
    }
    public double getThrottle() {
        return driverJoystick.getRawAxis(4);
    }
    private double getScale() {
        return !driverJoystick.getRawButton(Buttons.scaleDriveCoordinates) ? Robot.Drive.normalScale : Robot.Drive.driveCoordinateScale;
    }
}

