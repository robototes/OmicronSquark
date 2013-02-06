package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.DriveWithJoysticks;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends PIDSubsystem implements Maps {
    
    Victor frontLeftVictor = new Victor(Robot.Drive.frontLeftWheel);
    Victor rearLeftVictor = new Victor(Robot.Drive.rearLeftWheel);
    Victor frontRightVictor = new Victor(Robot.Drive.frontRightWheel);
    Victor rearRightVictor = new Victor(Robot.Drive.rearRightWheel);

    private RobotDrive robotDrive = new RobotDrive(frontLeftVictor, rearLeftVictor, frontRightVictor, rearRightVictor);
    private Gyro gyroscope = new Gyro(Robot.Drive.gyroscope);
    
    public DriveTrain() {    
<<<<<<< HEAD
        super(Robot.Drive.P, Robot.Drive.I, Robot.Drive.D);
        setInputRange(-360, 360);
        setAbsoluteTolerance(Robot.Drive.absoluteTolerance);
        this.getPIDController().setOutputRange(-1.0, 1.0);
        this.getPIDController().setContinuous(true);
        gyroscope.setSensitivity(Robot.Drive.gyroVoltsPerDegreeSecond);
=======
        super(Robot.Drive.P, Robot.Drive.I, Robot.Drive.D);                
        setInputRange(-359,359);
        gyroscope.setSensitivity(Constants.gyroVoltsPerDegreeSecond);  
>>>>>>> Improved gyroscope accuracy
        setAbsoluteTolerance(Constants.significanceLevel_Angle);
    }

    public void stop() {
        robotDrive.stopMotor();
        disable();
    }
    
    public void drive(double x, double y, double z) {
        disable(); // disables PID
        x = MathUtils.pow(x, 3);
        y = MathUtils.pow(y, 3);
        robotDrive.mecanumDrive_Cartesian(x, y, z, 0.0);
        SmartDashboard.putNumber("GYRO ANGLE", getGyroAngle());
    }

    public double distanceLeft() {
        return getPosition() - getSetpoint();
    }

    public void rotateTo(double angle) { // in degrees
        enable();
        setSetpoint(angle);
    }
    
    public void resetGyro() {
        Watchdog.getInstance().setEnabled(false);
        gyroscope.reset();
        Watchdog.getInstance().setEnabled(true);
<<<<<<< HEAD
=======
        setSetpoint(angle);
        enable(); // enables PID        
>>>>>>> Improved gyroscope accuracy
    }    
    
    public void reset() {
        gyroscope.reset();
    }

    protected void usePIDOutput(double output) {
        robotDrive.mecanumDrive_Polar(0.0, 0.0, output);
        SmartDashboard.putNumber("PID OUTPUT", output);
    }

    protected double returnPIDInput() {
<<<<<<< HEAD
        double angle = getGyroAngle();
        SmartDashboard.putNumber("GYRO ANGLE", angle);
        return angle;
    }
    
    public double getGyroAngle() {
        return gyroscope.getAngle() % 360;
    }
    
    public double normalizedToDegrees(double degrees) {
        return degrees / 360.0;
    }
    
    public double degreesToNormalized(double normalized) {
        return normalized * 360.0;
=======
        double angle = gyroscope.getAngle();
        SmartDashboard.putNumber("GYRO ANGLE", angle);
        return angle;
>>>>>>> Improved gyroscope accuracy
    }

    public void initDefaultCommand() {
        resetGyro();
        setDefaultCommand(new DriveWithJoysticks());
    }
}

