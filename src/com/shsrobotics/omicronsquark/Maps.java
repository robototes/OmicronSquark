package com.shsrobotics.omicronsquark;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public interface Maps {
    Joystick joystick = new Joystick(1); // the joystick is plugged in to the 1st USB port
    
    Relay.Value ON = Relay.Value.kForward;
    Relay.Value OFF = Relay.Value.kOff;
	Relay.Value REVERSE = Relay.Value.kReverse;

    DoubleSolenoid.Value EXTENDED = DoubleSolenoid.Value.kForward;
    DoubleSolenoid.Value RETRACTED = DoubleSolenoid.Value.kReverse;
	
    public static final class Robot {
        public static final class Drive {
            public static final int // ports that motors, sensors, etc. are plugged into
                frontLeftWheel = 3, // PWM
                frontRightWheel = 2, // PWM
                rearLeftWheel = 4, // PWM
                rearRightWheel = 1, // PWM
                gyroscope = 1; // Analog
            public static final double
                driveCoordinateScale = 0.5,
                normalScale = 1.0,
                P = 0.025,
                I = 0.0025,
                D = 0.00,
                absoluteTolerance = 0.75,
                gyroVoltsPerDegreeSecond = 0.007;
            public static final boolean
                encoderReverseDirection = false;
        } 
        public static final class Scorer {
            public static final int                
                flywheelFront = 6, // PWM
                flywheelRear = 5, // PWM
                loader = 1, // Relay
                frisbeeCounterSwitch = 1, // Digital IO
                loaderRegulatorSwitch = 2; // Digital IO
        }
        public static final class Climber {
            public static final int // ports				
                pistonLifterA = 1, // Solenoid
                pistonLifterB = 2, // Solenoid
                leftTalon = 7, // PWM
                rightTalon = 8; // PWM
        }
    }
    public static final class Constants {
        public static final double
            fieldLength = 54, // feet
            cameraHorizontalViewAngle = 47, // degrees
            cameraVerticalViewAngle = 36, // degrees
            shooterAngleAdjustment = -11,
            shooterVerticalAngle = 37,		// degrees
            significanceLevel_Angle = 2.0, //degrees
            significanceLevel_Rectangularity = 40, //percent            
            significanceLevel_Percent = 20, //percent            
            joystickThreshold = 0.1,
            momentumDelay = 0.075, // seconds
            rotationStep = 15.0,
            spinRight = 1.0,
            spinLeft = -1.0,
            shooterSpeedIncrement = 0.05,
            rearMotorScaling = -0.8,
			speedUpDelay = 4.5;
        
        public static final class aspectRatios {
            public static final double
                lowGoal = (29 + 8) / (24 + 8),
                middleGoal = (54 + 8) / (21 + 8),
                highGoal = (54 + 8) / (12 + 8);
        }
        
        public static final int
            onTargetCount = 5,
            failsAspectRatioTest = 0,
            lowGoal = 1,
            middleGoal = 2,
            highGoal = 3,
            groundLevel = 0,
            firstLevel = 1,
            secondLevel = 2,
            thirdLevel = 3;
    }
    
    public static final class Buttons {
        public static final JoystickButton 
     		climberDown = new JoystickButton(joystick, 2),
			climberUp = new JoystickButton(joystick, 3),
			shoot = new JoystickButton(joystick, 1),
			cancelLockToY = new JoystickButton(joystick, 12),
			getRidOfOneFrisbee = new JoystickButton(joystick, 10),
			deployPistons = new JoystickButton(joystick, 8),
			zeroGyro = new JoystickButton(joystick, 11),
			alignRobot = new JoystickButton(joystick, 7);
		
		public static final DigitalIOButton
			frisbeeCounter = new DigitalIOButton(Robot.Scorer.frisbeeCounterSwitch);
        
        // buttons that are only read for values
        public static final int
            scaleDriveCoordinates = 9,
			reverseLoader = 4;
    }
}
