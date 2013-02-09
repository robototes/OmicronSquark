
package com.shsrobotics.omicronsquark;

import com.shsrobotics.omicronsquark.commands.*;
import com.sun.squawk.util.MathUtils;

public class OI implements Maps {

    public OI() {
        Buttons.alignRobot.whenPressed(new AlignToShoot());
        Buttons.zeroGyro.whenPressed(new ResetGyroscope());
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
    public double getScale() {
        return !joystick.getRawButton(Buttons.scaleDriveCoordinates) ? Robot.Drive.normalScale : MathUtils.pow(Robot.Drive.driveCoordinateScale, -3);
    }
}

