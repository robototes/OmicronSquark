package com.shsrobotics.omicronsquark;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import com.shsrobotics.library.Button;
import com.shsrobotics.library.JoystickButton;

public interface Maps {
    Joystick driverJoystick = new Joystick(1); // the joystick is plugged in to the 1st USB port
	Joystick shooterJoystick = new Joystick(2); //						"			2nd USB port
    
    Relay.Value
		FORWARD = Relay.Value.kForward,
		ON = Relay.Value.kForward,
		OFF = Relay.Value.kOff,
		REVERSE = Relay.Value.kReverse;

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
                normalScale = 1.0;
        } 
        public static final class Scorer {
            public static final int                
                flywheelFront = 6, // PWM
                flywheelRear = 5, // PWM
                loader = 1, // Relay
                frisbeeCounterSwitch = 1, // Digital IO
                loaderRegulatorSwitch = 2; // Digital IO
        }
		
		public static final int
			compressorRelay = 3,
			compressorPressureSwitch = 5;
    }
    public static final class Constants {
        
		public static final double
			speedUpDelay = 4.2,
            rearMotorScaling = -0.85,
			idlePercent = 0.25,
			dialMaximumChangePercentage = 0.10,
			defaultDumpingValue = 0.30,
			defaultShootingBehindPyramidValue = 0.49,
			defaultShootingNextToPyramidValue = 0.94,
			fullPower = 1.0;
        
        public static final int
			dumpFudgeFactor = 1,
			towerBackFudgeFactor = 2,
			towerSideFudgeFactor = 4,
			TOWER_BACK = 1,
			TOWER_FRONT = 2;
    }
    
    public static final class Buttons {
        public static final Button 
			shoot = new JoystickButton(shooterJoystick, 1),
			loaderReverse = new JoystickButton(shooterJoystick, 2),
			loaderForwards = new JoystickButton(shooterJoystick, 3),
			loaderForwardsD = new JoystickButton(driverJoystick, 1),
			bringWheelsToSpeedToShootFromSide = new JoystickButton(shooterJoystick, 4),
			bringWheelsToSpeedToShootFromBack = new JoystickButton(shooterJoystick, 5),
			bringWheelsToSpeedToDump = new JoystickButton(shooterJoystick, 6),
			idleShooterWheels = new JoystickButton(shooterJoystick, 7),
			override = new JoystickButton(shooterJoystick, 12),
			scaleDriveCoordinates = new JoystickButton(driverJoystick, 2);
    }
}
