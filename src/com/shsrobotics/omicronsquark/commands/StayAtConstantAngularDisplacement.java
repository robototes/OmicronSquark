/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shsrobotics.omicronsquark.commands;

/**
 *
 * @author Owner
 */


public class StayAtConstantAngularDisplacement extends CommandBase {
    //double goalAngle;
    
    public StayAtConstantAngularDisplacement() {
        requires(driveTrain);
    }
    
    protected void initialize() {
       
    }

    protected void execute() {
        driveTrain.rotateTo(0);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
