/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.shsrobotics.omicronsquark;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *		ROBOT CODE.
 * YEAR:				2013
 * CHALLENGE:			ULTIMATE ASCENT
 * ROBOT CODE NAME:		OmicronSquark
 * TEAM:				2412
 * 
 * @author FIRST Robotics Team 2412
 */
public class Main extends IterativeRobot implements Maps, Hardware {

    SendableChooser position = new SendableChooser();
	
	double dumpPercent;
	double backPercent;
	double sidePercent;
    
    public void robotInit() {
        position.addDefault("Behind Pyramid", new Integer(Constants.TOWER_BACK));
        position.addObject("In Front of Pyramid", new Integer(Constants.TOWER_FRONT));
		SmartDashboard.putData("Robot Starting Position", position);
		
		compressor.start();
    }

    public void autonomousInit() {
		switch (((Integer) position.getSelected()).intValue()) {
			case Constants.TOWER_BACK:
				setFlywheels(Constants.defaultShootingBehindPyramidValue + 0.05);
				break;
			case Constants.TOWER_FRONT:
				setFlywheels(Constants.fullPower);
				break;
		}
		Timer.delay(Constants.speedUpDelay);
		Scorer.diskLoader.set(FORWARD);
    }
	
    public void teleopPeriodic() {
		dumpPercent = -2.5 - Math.floor(100 * shooterJoystick.getRawAxis(Constants.dumpFudgeFactor)) / 10;
		backPercent = -2.5 - Math.floor(100 * shooterJoystick.getRawAxis(Constants.towerBackFudgeFactor)) / 10;
		sidePercent = -2.5 - Math.floor(100 * shooterJoystick.getRawAxis(Constants.towerSideFudgeFactor)) / 10;
		
		
		Scorer.diskLoader.set( (Buttons.loaderForwards.held() || Buttons.loaderForwardsD.held()) ? FORWARD : OFF );
		Scorer.diskLoader.set( Buttons.loaderReverse.held() ? REVERSE : OFF );
		
		setFlywheels(Buttons.override.held() ? Constants.fullPower : 0.0);
		setFlywheels(Buttons.bringWheelsToSpeedToDump.held() ? Constants.defaultDumpingValue + dumpPercent / 100 : 0.0);
		setFlywheels(Buttons.bringWheelsToSpeedToShootFromBack.held() ? Constants.defaultShootingBehindPyramidValue + backPercent / 100 : 0.0);
		setFlywheels(Buttons.bringWheelsToSpeedToShootFromSide.held() ? Constants.defaultShootingNextToPyramidValue + sidePercent / 100 : 0.0);
		setFlywheels(Buttons.idleShooterWheels.held() ? Constants.idlePercent : 0.0);
		
		
		double scaleFactor = Buttons.scaleDriveCoordinates.held() ? Robot.Drive.driveCoordinateScale : Robot.Drive.normalScale;
		double X = driverJoystick.getX() * scaleFactor;
		double Y = driverJoystick.getY() * scaleFactor;
		double Z = driverJoystick.getZ() * scaleFactor;
		drive.mecanumDrive_Cartesian(X, Y, Z, 0.0);
		
		updateDashboard();
    }
	
	public void updateDashboard() {
		SmartDashboard.putString("DUMP Adjustment", dumpPercent + "%");
		SmartDashboard.putNumber("DUMP Adjustment Factor", dumpPercent);
		SmartDashboard.putString("BACK Adjustment", backPercent + "%");
		SmartDashboard.putNumber("BACK Adjustment Factor", backPercent);
		SmartDashboard.putString("SIDE Adjustment", sidePercent + "%");
		SmartDashboard.putNumber("SIDE Adjustment Factor", sidePercent);
	}
	
	public void setFlywheels(double power) {
		Scorer.flywheelMotorFront.set(power);
		Scorer.flywheelMotorRear.set(power * Constants.rearMotorScaling);
	}
}