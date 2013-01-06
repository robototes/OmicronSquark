
package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.*;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.sun.squawk.util.MathUtils;

public class DriveTrain extends Subsystem implements Maps {

      private RobotDrive robotDrive = new RobotDrive(Robot.frontLeftWheel, Robot.rearLeftWheel, Robot.frontRightWheel, Robot.rearRightWheel);
      private double scalingFactor = MathUtils.pow(1 - Constants.joystickThreshold, -3);

      public void drive(double x, double y, double z) {
	    x = MathUtils.pow(x, 3);
		  x = (x > Constants.joystickThreshold) ? (x - Constants.joystickThreshold) * scalingFactor : 0.0;
	    y = MathUtils.pow(y, 3);
		  y = (y > Constants.joystickThreshold) ? (y - Constants.joystickThreshold) * scalingFactor : 0.0;
	    robotDrive.mecanumDrive_Cartesian(x, y, z, 0.0);
      }

      public void stop() {
	    robotDrive.stopMotor();
      }

      public void initDefaultCommand() {
	    setDefaultCommand(new DriveWithJoysticks());
      }
}

