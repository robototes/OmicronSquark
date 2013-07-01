package com.shsrobotics.omicronsquark;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import com.shsrobotics.omicronsquark.Maps.Robot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;

/**
 * Robot Hardware.
 * @author Team 2412 <first.robototes.com, github.com/robototes>
 */
public interface Hardware {
	public class Wheels {
		private static Jaguar frontLeftJaguar = new Jaguar(Robot.Drive.frontLeftWheel);
		private static Jaguar rearLeftJaguar = new Jaguar(Robot.Drive.rearLeftWheel);
		private static Jaguar frontRightJaguar = new Jaguar(Robot.Drive.frontRightWheel);
		private static Jaguar rearRightJaguar = new Jaguar(Robot.Drive.rearRightWheel);
	
	}
	
	public class Scorer {
		public static Jaguar flywheelMotorFront = new Jaguar(Robot.Scorer.flywheelFront);
		public static Jaguar flywheelMotorRear = new Jaguar(Robot.Scorer.flywheelRear);
		public static Relay diskLoader = new Relay(Robot.Scorer.loader);
	}
	
	public static RobotDrive drive = new RobotDrive(Wheels.frontLeftJaguar, Wheels.rearLeftJaguar, Wheels.frontRightJaguar, Wheels.rearRightJaguar);
	public static Compressor compressor = new Compressor(Robot.compressorPressureSwitch, Robot.compressorRelay);
}
