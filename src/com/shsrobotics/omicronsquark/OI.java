
package com.shsrobotics.omicronsquark;

import com.shsrobotics.omicronsquark.commands.*;

public class OI implements Maps {

    public OI() {
        Buttons.alignRobot.whenPressed(new AlignToGoal());
        Buttons.stayAtRotation.whenPressed(new StayAtConstantAngularDisplacement());
        Buttons.resetGyro.whenPressed(new ResetGyro());
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
        return !joystick.getRawButton(Buttons.scaleDriveCoordinates) ? Robot.Drive.normalScale : Robot.Drive.driveCoordinateScale;
    }
}

