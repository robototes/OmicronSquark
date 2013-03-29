package com.shsrobotics.omicronsquark;

import com.shsrobotics.omicronsquark.commands.*;

public class OI implements Maps {

    public OI() {
		Buttons.loaderForwards.whenActive(new LoaderForward());
			Buttons.loaderForwards.whenInactive(new LoaderOff());
		Buttons.loaderForwardsD.whenActive(new LoaderForward());
			Buttons.loaderForwardsD.whenInactive(new LoaderOff());
		Buttons.loaderReverse.whenActive(new LoaderReverse());			
			Buttons.loaderReverse.whenInactive(new LoaderOff());
		Buttons.bringWheelsToSpeedToDump.whileHeld(new BringWheelsToSpeedForDumping());
			Buttons.bringWheelsToSpeedToDump.whenReleased(new ZeroFlywheels());
		Buttons.bringWheelsToSpeedToShootFromSide.whileHeld(new BringWheelsToSpeedForPyramidSide());
			Buttons.bringWheelsToSpeedToShootFromSide.whenReleased(new ZeroFlywheels());
		Buttons.bringWheelsToSpeedToShootFromBack.whileHeld(new BringWheelsToSpeedForPyramidBack());
			Buttons.bringWheelsToSpeedToShootFromBack.whenReleased(new ZeroFlywheels());
		Buttons.idleShooterWheels.whenActive(new LoaderForward());
			Buttons.idleShooterWheels.whenInactive(new LoaderOff());
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

