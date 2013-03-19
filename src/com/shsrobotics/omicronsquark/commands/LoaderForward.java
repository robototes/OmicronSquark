package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Global;
import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.Timer;

public class LoaderForward extends CommandBase implements Maps {
	
	Timer timer = new Timer();
	int lastSecond;
	
	public LoaderForward() {
		requires(diskShooter);
	}

	protected void initialize() {
		diskShooter.setLoader(ON);
		lastSecond = -1;
		timer.reset();
		timer.start();
	}

	protected void execute() {
		
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}