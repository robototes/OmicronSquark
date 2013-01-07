package com.shsrobotics.omicronsquark;

import edu.wpi.first.wpilibj.Joystick;

public interface Maps {
      Joystick joystick = new Joystick(1); // the joystick is plugged in to the 1st USB port

      public static final class Robot {

	    public static final class Drive {
		  public static final int // ports that motors, sensors, etc. are plugged into
			frontLeftWheel = 1,
			frontRightWheel = 1,
			rearLeftWheel = 1,
			rearRightWheel = 1,
			encoderASource = 1,
			encoderBSource = 1,
			encoderPulsesPerRevolution = 200;
		  public static final double
			driveCoordinateScale = 0.5,
			normalScale = 1.0,
			wheelDiameter = 6.0, // inches
			robotWidth = 20, // inches, distance between wheel centers (not bumpers)
			P = 0.1,
			I = 0.03,
			D = 0;
		  public static final boolean
			encoderreverseDirection = false;
	    }
      }

      public static final class Constants {
	    public static final double
		    joystickThreshold = 0.1,
		    fieldLength = 55; // feet
      }

      public static final class Buttons {

	    // buttons that are only read for values
	    public static final int
		    scaleDriveCoordinates = 1;
      }
}
