/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.shsrobotics.omicronsquark;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import com.shsrobotics.omicronsquark.commands.*;

public class Main extends IterativeRobot {

      Command stateMachine;

      public void robotInit() {
	    stateMachine = new StateMachine();

	    CommandBase.init(); // set up subsystems
      }

      public void autonomousInit() {
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
