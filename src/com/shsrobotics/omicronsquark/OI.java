package com.shsrobotics.omicronsquark;

import com.shsrobotics.omicronsquark.commands.*;
import com.sun.squawk.util.MathUtils;

public class OI implements Maps {

    public OI() {
        Buttons.alignRobot.whenPressed(new AlignToShoot());
        Buttons.zeroGyro.whenPressed(new ResetGyroscope());
		Buttons.driveForwards.whileHeld(new LockToYDirection());
		Buttons.shoot.whileHeld(new Shoot());
		Buttons.shooterUp.whenPressed(new IncreaseShooterSpeed());
		Buttons.shooterDown.whenPressed(new DecreaseShooterSpeed());
		Buttons.frisbeeCounter.whenPressed(new CountFrisbees());
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
    public double getThrottle() {
        return joystick.getRawAxis(4);
    }
    private double getScale() {
        return !joystick.getRawButton(Buttons.scaleDriveCoordinates) ? Robot.Drive.normalScale : MathUtils.pow(Robot.Drive.driveCoordinateScale, -3);
    }
}

