package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class AirCompressor extends Subsystem implements Maps {
	private Compressor compressor = new Compressor(Robot.compressorPressureSwitch, Robot.compressorRelay);

	public void turnOn() {
		compressor.start();
	}
	
	public void turnOff() {
		compressor.stop();
	}
	
	public void initDefaultCommand() { }
}
