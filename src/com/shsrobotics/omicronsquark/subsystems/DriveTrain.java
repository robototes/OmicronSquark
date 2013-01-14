
package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.DriveWithJoysticks;
import com.shsrobotics.omicronsquark.commands.StayAtConstantAngularDisplacement;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends PIDSubsystem implements Maps {

    private RobotDrive robotDrive = new RobotDrive(Robot.Drive.frontLeftWheel, Robot.Drive.rearLeftWheel, Robot.Drive.frontRightWheel, Robot.Drive.rearRightWheel);
    private Gyro gyroscope = new Gyro(Robot.Drive.gyroscope);

    public DriveTrain() {
        super(Robot.Drive.P, Robot.Drive.I, Robot.Drive.D);                
        setInputRange(0, 259);
        gyroscope.setSensitivity(0.0125);
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
        SmartDashboard.putNumber("GYRO ANGLE", gyroscope.getAngle());
        return gyroscope.getAngle();
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoysticks());
    }
}

