package com.shsrobotics.omicronsquark;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public interface Maps {
    Joystick joystick = new Joystick(1); // the joystick is plugged in to the 1st USB port
	
	DoubleSolenoid.Value EXTENDED = DoubleSolenoid.Value.kForward;
	DoubleSolenoid.Value RETRACTED = DoubleSolenoid.Value.kReverse;

    public static final class Robot {
        public static final class Drive {
            public static final int // ports that motors, sensors, etc. are plugged into
                frontLeftWheel = 1,
                frontRightWheel = 1,
                rearLeftWheel = 1,
                rearRightWheel = 1;
            public static final double
                driveCoordinateScale = 0.5,
                normalScale = 1.0,
                wheelDiameter = 6.0, // inches
                robotWidth = 20, // inches, distance between wheel centers (not bumpers)
                P = 0.1,
                I = 0.03,
                D = 0;				
            public static final boolean
                encoderReverseDirection = false;
        }
		public static final class Climber {
			public static final int // ports				
                lockDriveTrainPistonA = 1,
                lockDriveTrainPistonB = 2,
                limitSwitchUpperClimb = 1,
                limitSwitchLowerClimb = 2,
		leftTalon = 1,
		rightTalon = 1;
		}
    }

    public static final class Constants {
		public static final double
			joystickThreshold = 0.1,
			fieldLength = 54, // feet
			significanceLevel_Distance = 0.1, // feet
			significanceLevel_Angle = 2; //degrees
		public static final int
			groundLevel = 0,
			firstLevel = 1,
			secondLevel = 2,
			thirdLevel = 3;
    }

    public static final class Buttons {
		public static final JoystickButton 
			climb10 = new JoystickButton(joystick, 12),
			climb20 = new JoystickButton(joystick, 10),
			climb30 = new JoystickButton(joystick, 8);
		
		// buttons that are only read for values
		public static final int
			scaleDriveCoordinates = 1;
    }
}
