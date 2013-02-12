
package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.*;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem implements Maps {
    
    private RobotDrive robotDrive = new RobotDrive(Robot.Drive.frontLeftWheel,
	    Robot.Drive.rearLeftWheel, Robot.Drive.frontRightWheel, Robot.Drive.rearRightWheel);
    
    public void stop() {
        robotDrive.stopMotor();
    }
    
    public void drive(double x, double y, double z) {
            x = MathUtils.pow(x, 3);
            y = MathUtils.pow(y, 3);
            robotDrive.mecanumDrive_Cartesian(x, y, z, 0.0);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoysticks());
    }

}