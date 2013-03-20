package com.shsrobotics.omicronsquark;

import com.shsrobotics.omicronsquark.commands.*;
import com.sun.squawk.util.MathUtils;

public class OI implements Maps {

    public OI() {
        Buttons.alignRobot.whenPressed(new AlignToShoot());
        Buttons.zeroGyro.whenPressed(new ResetGyroscope());
		Buttons.addVirtualFrisbee.whenPressed(new AddVirtualFrisbee());
		Buttons.removeVirtualFrisbee.whenPressed(new RemoveVirtualFrisbee());
		Buttons.loaderForwards.whileHeld(new LoaderForward());
			Buttons.loaderForwards.whenReleased(new LoaderOff());
		Buttons.loaderReverse.whileHeld(new LoaderReverse());			
			Buttons.loaderReverse.whenReleased(new LoaderOff());
		Buttons.climberUp.whileHeld(new MoveClimberUp());
			Buttons.climberUp.whenReleased(new StopClimber());
		Buttons.climberDown.whileHeld(new MoveClimberDown());		
			Buttons.climberDown.whenReleased(new StopClimber());
		Buttons.shoot.whenPressed(new Shoot());
		Buttons.bringWheelsToSpeedToDump.whileHeld(new BringWheelsToSpeedForDumping());
			Buttons.bringWheelsToSpeedToDump.whenReleased(new ZeroFlywheels());
		Buttons.bringWheelsToSpeedToShootFromSide.whileHeld(new BringWheelsToSpeedForPyramidBack());
			Buttons.bringWheelsToSpeedToShootFromSide.whenReleased(new ZeroFlywheels());
		Buttons.bringWheelsToSpeedToShootFromBack.whileHeld(new BringWheelsToSpeedForPyramidSide());
			Buttons.bringWheelsToSpeedToShootFromBack.whenReleased(new ZeroFlywheels());
		Buttons.deployPistons.whenActive(new DeployPistons());
		Buttons.deployPistons.whenInactive(new UnDeployPistons());
		Buttons.idleShooterWheels.whenActive(new Idle());
			Buttons.idleShooterWheels.whenInactive(new ZeroFlywheels());
		Buttons.shootOrClimb.whenActive(new ShootMode());
			Buttons.shootOrClimb.whenInactive(new ClimbMode());
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
        return !driverJoystick.getRawButton(Buttons.scaleDriveCoordinates) ? Robot.Drive.normalScale : MathUtils.pow(Robot.Drive.driveCoordinateScale, -3);
    }
}

