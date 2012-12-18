package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class DriveWithJoysticks extends CommandBase implements Maps {

      public DriveWithJoysticks() {
	    requires(driveTrain);
      }

      protected void initialize() { }

      protected void execute() {
	    driveTrain.drive(oi.getForward(), oi.getTurn());
      }

      protected boolean isFinished() {
	    return false;
      }

      protected void end() { }

      protected void interrupted() {
	    driveTrain.drive(0, 0);
      }
}
