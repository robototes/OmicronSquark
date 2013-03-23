package com.shsrobotics.omicronsquark;

import com.shsrobotics.omicronsquark.commands.*;
import com.sun.squawk.util.MathUtils;

public class OI implements Maps {

    public OI() {
        Buttons.zeroGyro.whenPressed(new ResetGyroscope());
		Buttons.loaderForwards.whenActive(new LoaderForward());
			Buttons.loaderForwards.whenInactive(new LoaderOff());
		Buttons.loaderForwardsD.whenActive(new LoaderForward());
			Buttons.loaderForwardsD.whenInactive(new LoaderOff());
		Buttons.loaderReverse.whenActive(new LoaderReverse());			
			Buttons.loaderReverse.whenInactive(new LoaderOff());
		Buttons.climberUp.whileHeld(new MoveClimberUp());
			Buttons.climberUp.whenReleased(new StopClimber());
		Buttons.climberDown.whileHeld(new MoveClimberDown());		
			Buttons.climberDown.whenReleased(new StopClimber());
		Buttons.shoot.whenPressed(new Shoot());
		Buttons.bringWheelsToSpeedToDump.whileHeld(new BringWheelsToSpeedForDumping());
			Buttons.bringWheelsToSpeedToDump.whenReleased(new ZeroFlywheels());
		Buttons.bringWheelsToSpeedToShootFromSide.whileHeld(new BringWheelsToSpeedForPyramidSide());
			Buttons.bringWheelsToSpeedToShootFromSide.whenReleased(new ZeroFlywheels());
		Buttons.bringWheelsToSpeedToShootFromBack.whileHeld(new BringWheelsToSpeedForPyramidBack());
			Buttons.bringWheelsToSpeedToShootFromBack.whenReleased(new ZeroFlywheels());
		Buttons.deployPistons.whenActive(new DeployPistons());
		Buttons.deployPistons.whenInactive(new UnDeployPistons());
		Buttons.idleShooterWheels.whenActive(new LoaderForward());
			Buttons.idleShooterWheels.whenInactive(new LoaderOff());
		Buttons.shootOrClimb.whenActive(new ShootMode());
			Buttons.shootOrClimb.whenInactive(new ClimbMode());
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

