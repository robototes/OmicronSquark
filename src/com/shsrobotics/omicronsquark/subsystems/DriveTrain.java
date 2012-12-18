
package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.*;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.sun.squawk.util.MathUtils;

public class DriveTrain extends Subsystem implements Maps {

      private RobotDrive robotDrive = new RobotDrive(Robot.leftWheels, Robot.rightWheels);
      private double scalingFactor = MathUtils.pow(1 - Constants.joystickThreshold, -3);

      public void drive(double forward, double turn) {
	    forward = MathUtils.pow(forward, 3);
		  forward = (forward > Constants.joystickThreshold) ? (forward - Constants.joystickThreshold) * scalingFactor : 0.0;
	    turn = MathUtils.pow(turn, 3);
		  turn = (turn > Constants.joystickThreshold) ? (turn - Constants.joystickThreshold) * scalingFactor : 0.0;
	    robotDrive.arcadeDrive(forward, turn);
      }

      public void initDefaultCommand() {
	    setDefaultCommand(new DriveWithJoysticks());
      }
}

