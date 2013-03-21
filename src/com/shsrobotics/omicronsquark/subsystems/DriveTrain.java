package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.DriveWithJoysticks;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends PIDSubsystem implements Maps {
    
    private Jaguar frontLeftJaguar = new Jaguar(Robot.Drive.frontLeftWheel);
    private Jaguar rearLeftJaguar = new Jaguar(Robot.Drive.rearLeftWheel);
    private Jaguar frontRightJaguar = new Jaguar(Robot.Drive.frontRightWheel);
    private Jaguar rearRightJaguar = new Jaguar(Robot.Drive.rearRightWheel);

    private RobotDrive robotDrive = new RobotDrive(frontLeftJaguar, rearLeftJaguar, frontRightJaguar, rearRightJaguar);
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
        return (gyroscope.getAngle() % 360);
    }
	
	public void disable() {
		this.getPIDController().reset();
	}
    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoysticks());
    }	
}