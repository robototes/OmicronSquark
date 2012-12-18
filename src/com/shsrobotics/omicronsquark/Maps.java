package com.shsrobotics.omicronsquark;

import edu.wpi.first.wpilibj.Joystick;

public interface Maps {
      Joystick joystick = new Joystick(1);

      public static final class Robot {
	    public static final int
		    leftWheels = 1,
		    rightWheels = 1;
      }

      public static final class Constants {
	    public static final double
		    driveCoordinateScale = 0.5,
		    normalScale = 1.0,
		    joystickThreshold = 0.1;
      }

      public static final class Buttons {

	    // buttons that are only read for values
	    public static final int
		    scaleDriveCoordinates = 1;
      }
}
