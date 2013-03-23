package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveClimberDown extends CommandBase implements Maps {
	
	public MoveClimberDown() {
		requires(climber);
	}

	protected void initialize() {
		climber.set(1.0);
	}

	protected void execute() {
		double count = climber.getEncoder();
		double percent = count / Robot.Climber.maxEncoderClicks;
		System.out.println(count);
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
