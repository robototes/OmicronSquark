
package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.*;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class DriveTrain extends PIDSubsystem implements Maps {

      private RobotDrive robotDrive = new RobotDrive(Robot.Drive.frontLeftWheel, Robot.Drive.rearLeftWheel, Robot.Drive.frontRightWheel, Robot.Drive.rearRightWheel);
      private Encoder encoder = new Encoder(Robot.Drive.encoderASource, Robot.Drive.encoderBSource, Robot.Drive.encoderReverseDirection);

      private double scalingFactor = MathUtils.pow(1 - Constants.joystickThreshold, -3);
      private static final int MOVING_X = 1;
      private static final int MOVING_Y = 2;
      private static final int TURNING = 3;
      private int state = MOVING_Y;

      public DriveTrain() {
	    super(Robot.Drive.P, Robot.Drive.I, Robot.Drive.D);
      }

      public void stop() {
	    robotDrive.stopMotor();
      }
      public void drive(double x, double y, double z) {
	    disable(); // disables PID
	    x = MathUtils.pow(x, 3);
		  x = (x > Constants.joystickThreshold) ? (x - Constants.joystickThreshold) * scalingFactor : 0.0;
	    y = MathUtils.pow(y, 3);
		  y = (y > Constants.joystickThreshold) ? (y - Constants.joystickThreshold) * scalingFactor : 0.0;
	    robotDrive.mecanumDrive_Cartesian(x, y, z, 0.0);
      }

      public void driveX(double distance) { // in feet
	    state = MOVING_X;
	    encoder.reset();
	    setSetpointRange(0, Constants.fieldLength);
	    double distancePerPulse = Math.PI * (Robot.Drive.wheelDiameter / 12) / Robot.Drive.encoderPulsesPerRevolution;
	    encoder.setDistancePerPulse(distancePerPulse);
	    enable(); // enables PID
	    setSetpoint(distance);
      }

      public void driveY(double distance) { // in feet
	    state = MOVING_Y;
	    encoder.reset();
	    setSetpointRange(0, Constants.fieldLength);
	    double distancePerPulse = Math.PI * (Robot.Drive.wheelDiameter / 12) / Robot.Drive.encoderPulsesPerRevolution;
	    encoder.setDistancePerPulse(distancePerPulse);
	    enable(); // enables PID
	    setSetpoint(distance);
      }

      public void rotateTo(double angle) { // in degrees
	    state = TURNING;
	    encoder.reset();
	    setSetpointRange(-360, 360);
	    double distancePerPulse = 360 * (Robot.Drive.wheelDiameter / Robot.Drive.encoderPulsesPerRevolution / Robot.Drive.robotWidth);
	    encoder.setDistancePerPulse(distancePerPulse);
	    enable(); // enables PID
	    setSetpoint(angle);
      }

      public double distanceLeft() {
	    return getSetpoint() - encoder.getDistance();
      }


      protected void usePIDOutput(double output) {
	    if (state == TURNING) {  // changes output depending on whether rotateTo or driveTo was called.
		  robotDrive.mecanumDrive_Cartesian(0.0, 0.0, output, 0.0);
	    } else if (state == MOVING_X) {
		  robotDrive.mecanumDrive_Cartesian(output, 0.0, 0.0, 0.0);
	    } else if (state == MOVING_Y) {
		  robotDrive.mecanumDrive_Cartesian(0.0, output, 0.0, 0.0);
	    }
      }

      protected double returnPIDInput() {
	    return encoder.getDistance();
      }


      public void initDefaultCommand() {
	    setDefaultCommand(new DriveWithJoysticks());
      }
}

