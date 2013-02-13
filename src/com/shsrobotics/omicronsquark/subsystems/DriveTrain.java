package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.DriveWithJoysticks;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends PIDSubsystem implements Maps {
    
    Victor frontLeftVictor = new Victor(Robot.Drive.frontLeftWheel);
    Victor rearLeftVictor = new Victor(Robot.Drive.rearLeftWheel);
    Victor frontRightVictor = new Victor(Robot.Drive.frontRightWheel);
    Victor rearRightVictor = new Victor(Robot.Drive.rearRightWheel);

    private RobotDrive robotDrive = new RobotDrive(frontLeftVictor, rearLeftVictor, frontRightVictor, rearRightVictor);
    private Gyro gyroscope = new Gyro(Robot.Drive.gyroscope);
	
	private double userAlignment = 0.0;
    
    public DriveTrain() {    
        super(Robot.Drive.P, Robot.Drive.I, Robot.Drive.D);                
        setInputRange(-359,359);
		this.getPIDController().setOutputRange(-1.0, 1.0);
        gyroscope.setSensitivity(Robot.Drive.gyroVoltsPerDegreeSecond); 	
        setAbsoluteTolerance(Constants.significanceLevel_Angle); 
		getPIDController().reset();
    }

    public void stop() {        
        disable();
		robotDrive.stopMotor();
    }
    
    public void drive(double x, double y, double z, boolean useGyro) {
        disable(); // disables PID
        x = MathUtils.pow(x, 3);
        y = MathUtils.pow(y, 3);
        z = MathUtils.pow(z, 3);
		if (Math.abs(z) < Constants.joystickThreshold) {
			z = 0.0;
		}
		double angle = getGyroAngle();
        double gyroAngle = useGyro ? angle : 0.0;
        robotDrive.mecanumDrive_Cartesian(x, y, z, gyroAngle);
        SmartDashboard.putNumber("GYRO ANGLE", angle);
    }
	
	public void driveRearWheels(double magnitude) {
		robotDrive.mecanumDrive_Cartesian(0.0, magnitude, 0.0, 0.0);
	}

    public void rotateTo(double angle) { // in degrees
        setSetpoint(angle);
        enable();
    }
    
    public void reset() {
        gyroscope.reset();
    }    
	
	public void setUserAlignment(double y) {
		userAlignment = y;
	}


    protected void usePIDOutput(double output) {
        robotDrive.mecanumDrive_Cartesian(0.0, userAlignment, output, 0.0);
    }

    protected double returnPIDInput() {
        double angle = getGyroAngle();
        SmartDashboard.putNumber("GYRO ANGLE", angle);
        return angle;
    }
    
    public double getGyroAngle() {
        return -1 * (gyroscope.getAngle() % 360);
    }
	
	public void disable() {
		this.getPIDController().reset();
	}
    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoysticks());
    }	
}