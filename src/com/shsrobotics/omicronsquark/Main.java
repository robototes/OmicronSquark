/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.shsrobotics.omicronsquark;


import com.shsrobotics.omicronsquark.commands.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Main extends IterativeRobot implements Maps {

	Command stateMachine;
	SendableChooser robotPlacement;

	public void robotInit() {
		robotPlacement = new SendableChooser();
		robotPlacement.addDefault("Near Right Corner", new Integer(Constants.nearRightCorner));
		robotPlacement.addObject("Near Left Corner", new Integer(Constants.nearLeftCorner));
		robotPlacement.addObject("Far Right Corner", new Integer(Constants.farRightCorner));
		robotPlacement.addObject("Far Left Corner", new Integer(Constants.farLeftCorner));
		SmartDashboard.putData("Initial Robot Placement", robotPlacement);

		CommandBase.init(); // set up subsystems
	}

	public void autonomousInit() {
		Integer selected = (Integer) robotPlacement.getSelected();
		stateMachine = new StateMachine(selected.intValue());
		stateMachine.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		stateMachine.cancel();
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
