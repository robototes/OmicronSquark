/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;

/**
 *
 * @author Max
 */
public class FindGoalAlignment extends CommandBase implements Maps
{
    public FindGoalAlignment() {
        requires(camera);
    }
    
    protected void initialize() {
        System.out.println(camera.getAlignmentAngle());
    }
    
    protected void execute() {
        
    }
    
    protected void interrupted() {
        ;
    }
    
    protected void end() {
        
    }
    
    protected boolean isFinished() {
        return true;
    }
}
