package com.shsrobotics.omicronsquark;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public interface Maps {
    Joystick joystick = new Joystick(1); // the joystick is plugged in to the 1st USB port
    
    Relay.Value ON = Relay.Value.kForward;
    Relay.Value OFF = Relay.Value.kOff;
    
    public static final class Robot {
        public static final class Drive {
            public static final int // ports that motors, sensors, etc. are plugged into
                frontLeftWheel = 3,
                frontRightWheel = 1,
                rearLeftWheel = 4,
                rearRightWheel = 2,
                gyroscope = 1;
            public static final double
                driveCoordinateScale = 0.5,
                normalScale = 1.0,
                P = 0.00665,
                I = 0.00015,
                D = 0.00,
                absoluteTolerance = 2.0,
                gyroVoltsPerDegreeSecond = 0.007;
            public static final boolean
                encoderReverseDirection = false;
        }        
    }
    
    public static final class Constants {
        public static final double
            fieldLength = 54, // feet
            cameraHorizontalViewAngle = 54, // degrees
            significanceLevel_Angle = 2, //degrees
            significanceLevel_Rectangularity = 40, //percent            
            significanceLevel_Percent = 20, //percent            
            servoJoystickScaling = 0.01,
            rotationStep = 30.0,
            spinRight = 1.0,
            spinLeft = -1.0;
        
        public static final class aspectRatios {
            public static final double
                lowGoal = 29 / 24,
                middleGoal = 54 / 21,
                highGoal = 54 / 12;
        }
        
        public static final int
            nearLeftCorner = 0,
            nearRightCorner = 1,
            farLeftCorner = 2,
            farRightCorner = 3,
            scoreImmediately = 0,
            scoreAfterDelay = 1,
            doNotScore = 2,
            failsAspectRatioTest = 0,
            lowGoal = 1,
            middleGoal = 2,
            highGoal = 3;
    }
    
    public static final class Buttons {
        public static final JoystickButton 
            alignRobot = new JoystickButton(joystick, 1),
            zeroGyro = new JoystickButton(joystick, 11);
        
        // buttons that are only read for values
        public static final int
            scaleDriveCoordinates = 1;
    }
}
