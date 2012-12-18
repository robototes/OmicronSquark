
package com.shsrobotics.omicronsquark;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import com.shsrobotics.omicronsquark.Maps;

public class OI implements Maps {

      public double getForward() {
	    return joystick.getY() * getScale();
      }
      public double getTurn() {
	    return joystick.getX() * getScale();
      }
      public double getScale() {
	    return joystick.getRawButton(Buttons.scaleDriveCoordinates) ? Constants.normalScale : Constants.driveCoordinateScale;
      }
}

