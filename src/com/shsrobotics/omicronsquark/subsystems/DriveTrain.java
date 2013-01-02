
package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.*;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Encoder;

public class DriveTrain extends PIDSubsystem implements Maps {

      private RobotDrive robotDrive = new RobotDrive(Robot.Drive.leftWheels, Robot.Drive.rightWheels);
      private Encoder encoder = new Encoder(Robot.Drive.Encoder.aSource, Robot.Drive.Encoder.bSource, Robot.Drive.Encoder.reverseDirection);

      private double scalingFactor = MathUtils.pow(1 - Constants.joystickThreshold, -3);
      private boolean turning = false;

      public DriveTrain() {
	    super(Robot.Drive.P, Robot.Drive.I, Robot.Drive.D);
      }

      public void drive(double forward, double turn) {
	    disable(); // disables PID
	    forward = MathUtils.pow(forward, 3);
		  forward = (forward > Constants.joystickThreshold) ? (forward - Constants.joystickThreshold) * scalingFactor : 0.0;
	    turn = MathUtils.pow(turn, 3);
		  turn = (turn > Constants.joystickThreshold) ? (turn - Constants.joystickThreshold) * scalingFactor : 0.0;
	    robotDrive.arcadeDrive(forward, turn);
      }

      public void driveTo(double distance) { // in feet
	    turning = false; // tells usePIDOutput() whether to make robot turn or go forward.
	    encoder.reset();
	    setSetpointRange(0, Constants.fieldLength);
	    double distancePerPulse = Math.PI * (Robot.Drive.wheelDiameter / 12) / Robot.Drive.Encoder.pulsesPerRevolution;
	    encoder.setDistancePerPulse(distancePerPulse);
	    enable(); // enables PID
	    setSetpoint(distance);
      }

      public void rotateTo(double angle) { // in degrees
	    turning = true; // tells usePIDOutput() whether to make robot turn or go forward.
	    encoder.reset();
	    setSetpointRange(-360, 360);
	    double distancePerPulse = 360 * (Robot.Drive.wheelDiameter / Robot.Drive.Encoder.pulsesPerRevolution / Robot.Drive.robotWidth);
	    encoder.setDistancePerPulse(distancePerPulse);
	    enable(); // enables PID
	    setSetpoint(angle);
      }

      public double distanceLeft() {
	    return getSetpoint() - encoder.getDistance();
      }


      protected void usePIDOutput(double output) {
	    if (turning) {  // changes output depending on whether rotateTo or driveTo was called.
		 robotDrive.arcadeDrive(0.0, output);
	    } else {
		 robotDrive.arcadeDrive(output, 0.0);
	    }
      }

      protected double returnPIDInput() {
	    return encoder.getDistance();
      }


      public void initDefaultCommand() {
	    setDefaultCommand(new DriveWithJoysticks());
      }
}

