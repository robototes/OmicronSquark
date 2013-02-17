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
	
    DoubleSolenoid.Value EXTENDED = DoubleSolenoid.Value.kForward;
    DoubleSolenoid.Value RETRACTED = DoubleSolenoid.Value.kReverse;
    
    public static final class Robot {
        public static final class Drive {
            public static final int // ports that motors, sensors, etc. are plugged into
                frontLeftWheel = 3,
                frontRightWheel = 2,
                rearLeftWheel = 4,
                rearRightWheel = 1,
                gyroscope = 1;
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
                encoderFrontA = 2,
                encoderFrontB = 3,
                encoderRearA = 4,
                encoderRearB = 5,
                flywheelFront = 5,
                flywheelRear = 6,
                loader = 1,
                frisbeeCounterSwitch = 6,
                loaderRegulatorSwitch = 7,
                encoderPulsesPerRevolution = 256;
            public static final double
                P = 0.0,
                I = 0.0,
                D = 0.0;
        }
        public static final class Climber {
            public static final int // ports				
                pistonLifterA = 1,
                pistonLifterB = 2,
                limitSwitchUpperClimb = 8,
                limitSwitchLowerClimb = 9,
                leftTalon = 7,
                rightTalon = 8;
        }
    }
    public static final class Constants {
        public static final double
            fieldLength = 54, // feet
            cameraHorizontalViewAngle = 47, // degrees
            cameraVerticalViewAngle = 36, // degrees
            shooterAngleAdjustment = -12,
            shooterVerticalAngle = 37,		// degrees
            significanceLevel_Angle = 3.0, //degrees
            significanceLevel_Rectangularity = 40, //percent            
            significanceLevel_Percent = 20, //percent            
            joystickThreshold = 0.1,
            momentumDelay = 0.075, // seconds
            rotationStep = 15.0,
            spinRight = 1.0,
            spinLeft = -1.0,
            frontToRearMotorSpeedRatio = 2000 / 5000, //rpm
            shooterSpeedIncrement = 0.05,
            rearMotorScaling = 0.8;
        
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
            alignRobot = new JoystickButton(joystick, 7),
            zeroGyro = new JoystickButton(joystick, 11),
            driveForwards = new JoystickButton(joystick, 2),
            shoot = new JoystickButton(joystick, 1),
            shooterUp = new JoystickButton(joystick, 6),
            shooterDown = new JoystickButton(joystick, 4),
            cancelLockToY = new JoystickButton(joystick, 12),
            getRidOfOneFrisbee = new JoystickButton(joystick, 10);

        public static final DigitalIOButton
            frisbeeCounter = new DigitalIOButton(Robot.Scorer.frisbeeCounterSwitch);		
        
        // buttons that are only read for values
        public static final int
            scaleDriveCoordinates = 9;
    }
}
