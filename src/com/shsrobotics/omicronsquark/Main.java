/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.shsrobotics.omicronsquark;


import com.shsrobotics.library.FRCRobot;
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
public class Main extends FRCRobot implements Hardware {

    SendableChooser position = new SendableChooser();
	
    double dumpPercent;
    double backPercent;
    double sidePercent;
    
    public void robotInit() {
		super.robotInit();
		
        position.addDefault("Behind Pyramid", new Integer(Constants.TOWER_BACK));
        position.addObject("In Front of Pyramid", new Integer(Constants.TOWER_FRONT));
		SmartDashboard.putData("Robot Starting Position", position);
		
		compressor.start();
    }

    public void autonomousInit() {
		switch ( ((Integer) position.getSelected()).intValue() ) {
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
		// raw values translated to percents
		dumpPercent = -2.5 - Math.floor(100 * shooterJoystick.getRawAxis(Constants.dumpFudgeFactor)) / 10;
		backPercent = -2.5 - Math.floor(100 * shooterJoystick.getRawAxis(Constants.towerBackFudgeFactor)) / 10;
		sidePercent = -2.5 - Math.floor(100 * shooterJoystick.getRawAxis(Constants.towerSideFudgeFactor)) / 10;
		
		// Disk loader
		if (Buttons.loaderForwards.held() || Buttons.loaderForwardsD.held()) {		// forward
			Scorer.diskLoader.set(FORWARD);
		} else if (Buttons.loaderReverse.held()) {									// reverse
			Scorer.diskLoader.set(REVERSE);
		} else {
			Scorer.diskLoader.set(OFF);
		}
		
		// Flywheels
		if (Buttons.idleShooterWheels.held()) {										// idle
			setFlywheels(Constants.fullPower);
		}
		
		if (Buttons.bringWheelsToSpeedToDump.held()) {											// dumping speed
			setFlywheels(Constants.defaultDumpingValue + dumpPercent / 100);
		} else if (Buttons.bringWheelsToSpeedToShootFromBack.held()) {							// pyramid back speed
			setFlywheels(Constants.defaultShootingBehindPyramidValue + backPercent / 100);
		} else if (Buttons.bringWheelsToSpeedToShootFromSide.held()) {							// pyramid side speed
			setFlywheels(Constants.defaultShootingNextToPyramidValue + sidePercent / 100);
		} else if (Buttons.override.held()) {													// override
			setFlywheels(Constants.idlePercent);
		} else {
			setFlywheels(0.0);
		}		
		
		double scaleFactor = Buttons.scaleDriveCoordinates.held() ? Robot.Drive.driveCoordinateScale : Robot.Drive.normalScale;
		double X = driverJoystick.getX() * scaleFactor;
		double Y = driverJoystick.getY() * scaleFactor;
		double Z = driverJoystick.getZ() * scaleFactor;
		drive.mecanumDrive_Cartesian(X, Y, Z, noGyroscope);
		
		updateDashboard();
    }
	
	public void updateDashboard() {
		SmartDashboard.putString("DUMP Adjustment", dumpPercent + "%");		// numeric value
		SmartDashboard.putNumber("DUMP Adjustment Factor", dumpPercent);	// slider bar
		SmartDashboard.putString("BACK Adjustment", backPercent + "%");		// numberic value
		SmartDashboard.putNumber("BACK Adjustment Factor", backPercent);	// slider bar
		SmartDashboard.putString("SIDE Adjustment", sidePercent + "%");		// numberic value
		SmartDashboard.putNumber("SIDE Adjustment Factor", sidePercent);	// slider bar
	}
	
	public void setFlywheels(double power) {
		Scorer.flywheelMotorFront.set(power);
		Scorer.flywheelMotorRear.set(power * Constants.rearMotorScaling);
	}
}