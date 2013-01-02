package com.shsrobotics.omicronsquark;

import edu.wpi.first.wpilibj.Joystick;

public interface Maps {
      Joystick joystick = new Joystick(1);

      public static final class Robot {
	    public static final class Drive {
		  public static final int
			  leftWheels = 1,
			  rightWheels = 1;
		  public static final double
			  driveCoordinateScale = 0.5,
			  normalScale = 1.0,
			  wheelDiameter = 6.0, // inches
			  robotWidth = 20, // inches, distance between wheel centers (not bumpers)
			  P = 0.1,
			  I = 0.03,
			  D = 0;
		  public static final class Encoder {
			public static final int
			      aSource = 1,
			      bSource = 1,
			      pulsesPerRevolution = 200;
			 public static final boolean
			      reverseDirection = false;
		  }
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
