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

/**
 * 2013 2412 CODE
 * @author RoboTotes Team 2412
 */
public class Main extends IterativeRobot implements Maps {

    Command stateMachine;
    SendableChooser position = new SendableChooser();
    
    public void robotInit() {
        CommandBase.init(); // set up subsystems
        position.addDefault("Behind Pyramid", new StateMachineBehindPyramid());
        position.addObject("In Front of Pyramid", new StateMachineFront());
		SmartDashboard.putData("Robot Position", position);
    }

    public void autonomousInit() {
        stateMachine = (Command) position.getSelected();
        stateMachine.start();
    }
	
	public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
	
    public void teleopInit() {
        if (stateMachine != null) {
			stateMachine.cancel();
		}
    }
    
    public void teleopPeriodic() {
        Scheduler.getInstance().run();		
		SmartDashboard.putString("DUMP Adjustment Factor", 
			Math.floor(100 * shooterJoystick.getRawAxis(Constants.dumpFudgeFactor)) / 10 + "%");
		SmartDashboard.putString("BACK Adjustment Factor", 
			Math.floor(100 * shooterJoystick.getRawAxis(Constants.towerBackFudgeFactor)) / 10 + "%");
		SmartDashboard.putString("SIDE Adjustment Factor", 
			Math.floor(100 * shooterJoystick.getRawAxis(Constants.towerSideFudgeFactor)) / 10 + "%");
    }
}