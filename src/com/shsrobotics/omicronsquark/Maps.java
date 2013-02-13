package com.shsrobotics.omicronsquark;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public interface Maps {
    Joystick joystick = new Joystick(1);

    public static final class Robot {
        public static final class Scorer {
            public static final int
				encoder1A = 1,
				encoder1B = 1,
				encoder2A = 1,
				encoder2B = 1,
                flywheelFront = 5,
                flywheelRear = 6,
                loader = 9,
				frisbeeCounterSwitch = 1,
				shooterLimitSwitch = 1;
			public static final double
				P = 0.0,
				I = 0.0,
				D = 0.0;
        }
    }
    
    public static final class Constants {
        public static final int
            idlePercent = 5;
    }

    public static final class Buttons {
		public static final JoystickButton
			shoot = new JoystickButton(joystick, 1);
    }
}
