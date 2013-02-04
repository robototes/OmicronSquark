
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
        super(Robot.Drive.P, Robot.Drive.I, Robot.Drive.D);                
        setInputRange(0, 259);
        gyroscope.setSensitivity(Constants.gyroVoltsPerDegreeSecond);        
    }

    public void stop() {
        robotDrive.stopMotor();
    }
    
    public void drive(double x, double y, double z) {
        disable(); // disables PID
        x = MathUtils.pow(x, 3);
        y = MathUtils.pow(y, 3);
        robotDrive.mecanumDrive_Cartesian(x, y, z, 0.0);
        SmartDashboard.putNumber("GYRO ANGLE", gyroscope.getAngle());
    }

    public double distanceLeft() {
        return getPosition() - getSetpoint();
    }

    public void rotateTo(double angle) { // in degrees
        Watchdog.getInstance().setEnabled(false);
        gyroscope.reset();
        Watchdog.getInstance().setEnabled(true);
        setSetpoint(angle);
        enable(); // enables PID
        
    }    

    protected void usePIDOutput(double output) {
        robotDrive.mecanumDrive_Cartesian(0.0, 0.0, output, 0.0);		
    }

    protected double returnPIDInput() {
        return gyroscope.getAngle();
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoysticks());
    }
}

