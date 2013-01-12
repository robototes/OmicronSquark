
package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.*;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import com.shsrobotics.omicronsquark.commands.CommandBase;

public class DriveTrain extends PIDSubsystem implements Maps {

	private RobotDrive robotDrive = new RobotDrive(Robot.Drive.frontLeftWheel, Robot.Drive.rearLeftWheel, Robot.Drive.frontRightWheel, Robot.Drive.rearRightWheel);
	private AbsoluteGyroscope gyroscope = new AbsoluteGyroscope(Robot.Drive.gyroscope);

	private double scalingFactor = MathUtils.pow(1 - Constants.joystickThreshold, -3);
	
	public DriveTrain() {
            super(Robot.Drive.P, Robot.Drive.I, Robot.Drive.D);                
            setInputRange(-360, 360);
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
            robotDrive.mecanumDrive_Cartesian(x, y, z, gyroscope.getAngularVelocity());
	}

	public double distanceLeft() {
            return getPosition() - getSetpoint();
        }

	public void rotateTo(double angle) { // in degrees
            gyroscope.reset();
            enable(); // enables PID
            setSetpoint(angle);
	}

        public double getAngle() {
            return gyroscope.getAngle();
        }
        
	protected void usePIDOutput(double output) {
            robotDrive.mecanumDrive_Cartesian(0.0, 0.0, output, 0.0);		
	}

	protected double returnPIDInput() {
            return CommandBase.camera.pidGet();
//            return gyroscope.getAngle();
	}

	public void initDefaultCommand() { }
}

