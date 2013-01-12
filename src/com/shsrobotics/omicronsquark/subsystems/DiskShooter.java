package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DiskShooter extends Subsystem implements Maps {
    
    private Jaguar flywheelMotor = new Jaguar(Robot.Scorer.flywheel);
    
    public void idle(boolean state) {
        double motorValue = state ? Constants.idlePercent / 100 : 0.0;
        flywheelMotor.set(motorValue);
    }
    
    public void set(double value) {
        flywheelMotor.set(value);
    }

    public void initDefaultCommand() { }
}
