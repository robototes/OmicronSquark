package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

public class MoveCameraWithJoystick extends CommandBase implements Maps {
    
    public MoveCameraWithJoystick() {
        requires(camera);
    }

    protected void initialize() { }

    protected void execute() {
        camera.joystickChange(oi.getX() * Constants.servoJoystickScaling, oi.getY() * Constants.servoJoystickScaling);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() { }
    
    protected void interrupted() { }
}
