package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveClimberUp extends CommandBase implements Maps{
	
		
	public MoveClimberUp() {
		requires(climber);
	}

	protected void initialize() {
		climber.set(-1.0);
	}

	protected void execute() {
		double percent = climber.getEncoder() / Robot.Climber.maxEncoderClicks;
		SmartDashboard.putNumber("CLIMBER", percent);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		climber.stop();
	}

	protected void interrupted() {
		climber.stop();
	}
}
