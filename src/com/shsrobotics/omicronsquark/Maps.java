package com.shsrobotics.omicronsquark;

import edu.wpi.first.wpilibj.Joystick;

public interface Maps {
    Joystick joystick = new Joystick(1);

    public static final class Robot {
        public static final class Scorer {
            public static final int
                flywheel = 1;
        }
    }
    
    public static final class Constants {
        public static final int
            idlePercent = 5;
    }

    public static final class Buttons {

    }
}
