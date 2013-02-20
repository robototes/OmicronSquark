package com.shsrobotics.omicronsquark.commands;

public class FillAirCanisters extends CommandBase {
	
	public FillAirCanisters() {
		requires(compressor);
	}

	protected void initialize() {
		compressor.turnOn();
	}

	protected void execute() { }
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() { }
	
	protected void interrupted() {
		compressor.turnOff();
	}
}
