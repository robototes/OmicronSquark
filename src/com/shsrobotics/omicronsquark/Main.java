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
    

    public void startCompetition() {
        try {
            super.startCompetition();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   

    public void robotInit() {
        CommandBase.init(); // set up subsystems
    }

    public void autonomousInit() {        
        stateMachine = new StateMachine();
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
    
    public void disabledPeriodic() {
        SmartDashboard.putNumber("GYRO ANGLE", CommandBase.driveTrain.getGyroAngle());
    }
}
