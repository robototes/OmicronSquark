package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.subsystems.*;
public class ShootOld extends CommandBase {

    public ShootOld() {
	requires(diskShooter);
    }

    protected void initialize() { }

    protected void execute() {
	diskShooter.set(0.9);
	diskShooter.shoot();
    }

    protected boolean isFinished() {
	return false;
    }

    protected void end() {
	diskShooter.stop();
    }

    protected void interrupted() {
	diskShooter.stop();
    }
}