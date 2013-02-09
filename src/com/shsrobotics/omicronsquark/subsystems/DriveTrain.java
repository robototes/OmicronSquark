package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.DriveWithJoysticks;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends PIDSubsystem implements Maps {
    
    Jaguar frontLeftJaguar = new Jaguar(Robot.Drive.frontLeftWheel);
    Jaguar rearLeftJaguar = new Jaguar(Robot.Drive.rearLeftWheel);
    Jaguar frontRightJaguar = new Jaguar(Robot.Drive.frontRightWheel);
    Jaguar rearRightJaguar = new Jaguar(Robot.Drive.rearRightWheel);

    private RobotDrive robotDrive = new RobotDrive(frontLeftJaguar, rearLeftJaguar, frontRightJaguar, rearRightJaguar);
    private Gyro gyroscope = new Gyro(Robot.Drive.gyroscope);
    
    public DriveTrain() {    
        super(Robot.Drive.P, Robot.Drive.I, Robot.Drive.D);                
        setInputRange(-359,359);
	this.getPIDController().setOutputRange(-1.0, 1.0);
        gyroscope.setSensitivity(Robot.Drive.gyroVoltsPerDegreeSecond); 	
        setAbsoluteTolerance(Constants.significanceLevel_Angle);        
        robotDrive.setExpiration(20);
    }

    public void stop() {
        robotDrive.stopMotor();
        disable();
    }
    
    public void drive(double x, double y, double z, boolean useGyro) {
        disable(); // disables PID
        x = MathUtils.pow(x, 3);
        y = MathUtils.pow(y, 3);
        double gyroAngle = useGyro ? getGyroAngle() : 0.0;
        robotDrive.mecanumDrive_Cartesian(x, y, z, gyroAngle);
        SmartDashboard.putNumber("GYRO ANGLE", getGyroAngle());
    }

    public void rotateTo(double angle) { // in degrees
        disable();
        setSetpoint(angle);
        enable();
    }
    
    public void reset() {
        gyroscope.reset();
    }    


    protected void usePIDOutput(double output) {
        robotDrive.mecanumDrive_Polar(0.0, 0.0, output);
        SmartDashboard.putNumber("PID OUTPUT", output);
    }

    protected double returnPIDInput() {
        double angle = getGyroAngle();
        SmartDashboard.putNumber("GYRO ANGLE", angle);
        return angle;
    }
    
    public double getGyroAngle() {
        return gyroscope.getAngle() % 360;
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoysticks());
    }
}

