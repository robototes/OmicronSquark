
package com.shsrobotics.omicronsquark;

import com.shsrobotics.omicronsquark.commands.*;
import com.sun.squawk.util.MathUtils;

public class OI implements Maps {

    public OI() {
        Buttons.alignRobot.whenPressed(new AlignToShoot());
        Buttons.zeroGyro.whenPressed(new ResetGyroscope());
		Buttons.stayAtZero.whenPressed(new RotateToConstant());
		Buttons.driveForwards.whileHeld(new DriveWithJoysticks());
    }
    
    public double getX() {
        return -0.9 * joystick.getX() * getScale();
    }
    public double getY() {
        return 0.9 * joystick.getY() * getScale();
    }
    public double getZ() {		
        return joystick.getZ() * getScale() * 0.65;
    }
    public double getThrottle() {
        return joystick.getRawAxis(4);
    }
    private double getScale() {
        return !joystick.getRawButton(Buttons.scaleDriveCoordinates) ? Robot.Drive.normalScale : MathUtils.pow(Robot.Drive.driveCoordinateScale, -3);
    }
}

