
package com.shsrobotics.omicronsquark.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.shsrobotics.omicronsquark.commands.*;

public class DriveTrain extends Subsystem {
      
      public void initDefaultCommand() {
	    setDefaultCommand(new DriveWithJoysticks());
      }
}

