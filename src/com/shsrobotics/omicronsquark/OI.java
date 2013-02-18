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
		Buttons.loaderReverse.whileHeld(new LoaderReverse());
		Buttons.climberUp.whileHeld(new MoveClimberUp());
		Buttons.climberDown.whileHeld(new MoveClimberDown());
		Buttons.shoot.whenPressed(new Shoot());
		Buttons.frisbeeCounter.whenPressed(new CountFrisbees());
		Buttons.bringWheelsToSpeed.whenPressed(new BringWheelsToSpeed());
		Buttons.deployPistons.whileHeld(new DeployPistons());
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

