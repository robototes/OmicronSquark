package com.shsrobotics.omicronsquark;

import edu.wpi.first.wpilibj.Joystick;
import com.shsrobotics.library.Button;
import com.shsrobotics.library.GLOBAL;
import com.shsrobotics.library.JoystickButton;
import com.shsrobotics.library.joysticks.Extreme3DController;

public interface Maps extends GLOBAL {
    Joystick driverJoystick = new Joystick(USB_1); 
	Joystick shooterJoystick = new Joystick(USB_2);
    
    public static final class Robot {
        public static final class Drive {
            public static final int // ports that motors, sensors, etc. are plugged into
                frontLeftWheel = PWM_3,
                rearLeftWheel = PWM_4,
                frontRightWheel = PWM_2,
                rearRightWheel = PWM_1;
            public static final double
                driveCoordinateScale = 0.5,
                normalScale = 1.0;
        } 
        public static final class Scorer {
            public static final int                
                flywheelFront = PWM_6, // PWM
                flywheelRear = PWM_5, // PWM
                loader = RELAY_1, // Relay
                frisbeeCounterSwitch = DIGITAL_IO_1, // Digital IO
                loaderRegulatorSwitch = DIGITAL_IO_2; // Digital IO
        }
		
		public static final int
			compressorRelay = RELAY_3,
			compressorPressureSwitch = DIGITAL_IO_5;
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
			loaderForwardsD = new JoystickButton(driverJoystick, Extreme3DController.trigger),
			bringWheelsToSpeedToShootFromSide = new JoystickButton(shooterJoystick, 4),
			bringWheelsToSpeedToShootFromBack = new JoystickButton(shooterJoystick, 5),
			bringWheelsToSpeedToDump = new JoystickButton(shooterJoystick, 6),
			idleShooterWheels = new JoystickButton(shooterJoystick, 7),
			override = new JoystickButton(shooterJoystick, 12),
			scaleDriveCoordinates = new JoystickButton(driverJoystick, Extreme3DController.side);
    }
}
