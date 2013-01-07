
package com.shsrobotics.omicronsquark;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import com.shsrobotics.omicronsquark.Maps;

public class OI implements Maps {

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

