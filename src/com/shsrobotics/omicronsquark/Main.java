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
import edu.wpi.first.wpilibj.communication.FRCControl;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Main extends IterativeRobot implements Maps {

    Command stateMachine;
    // chooser buttons put on SmartDashboard for configuring robot options
    SendableChooser robotPlacement;
    SendableChooser autonomousScore;

    public void startCompetition() {
        try {
            super.startCompetition();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   

    public void robotInit() {
        robotPlacement = new SendableChooser();
        robotPlacement.addDefault("Near Right Corner", new Integer(Constants.nearRightCorner));
        robotPlacement.addObject("Near Left Corner", new Integer(Constants.nearLeftCorner));
        robotPlacement.addObject("Far Right Corner", new Integer(Constants.farRightCorner));
        robotPlacement.addObject("Far Left Corner", new Integer(Constants.farLeftCorner));
        SmartDashboard.putData("Initial Robot Placement", robotPlacement);

        autonomousScore = new SendableChooser();
        autonomousScore.addDefault("Score during Autonomous", new Integer(Constants.scoreImmediately));
        autonomousScore.addObject("Wait " + Constants.scoringDelay + " seconds before scoring", new Integer(Constants.scoreAfterDelay));
        autonomousScore.addObject("Do not score during Autonomous", new Integer(Constants.doNotScore));
        SmartDashboard.putData("Autonomous Scoring Options", autonomousScore);

        CommandBase.init(); // set up subsystems
    }

    public void autonomousInit() {
        Integer placement = (Integer) robotPlacement.getSelected();
        Integer scoringOptions = (Integer) autonomousScore.getSelected();
        stateMachine = new StateMachine(placement.intValue(), scoringOptions.intValue());
        stateMachine.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (stateMachine != null) stateMachine.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
}
