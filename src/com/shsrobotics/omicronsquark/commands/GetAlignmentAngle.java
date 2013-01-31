
<!-- saved from url=(0138)https://raw.github.com/robototes/OmicronSquark/testing_getAlignmentAngle/src/com/shsrobotics/omicronsquark/commands/GetAlignmentAngle.java -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></head><body class=" hasGoogleVoiceExt"><pre style="word-wrap: break-word; white-space: pre-wrap;">package com.shsrobotics.omicronsquark.commands;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GetAlignmentAngle extends CommandBase implements Maps {
    
    boolean isDone = false;
    
    public GetAlignmentAngle() {
        requires(camera);
        requires(driveTrain);
    }

    protected void initialize() { }

    protected void execute() {
        double angle = camera.getAlignmentAngle();
        SmartDashboard.putNumber("Horizontal Angle From Goal", angle);
        isDone = true;
    }

    protected boolean isFinished() {
        return isDone;
    }

    protected void end() { }

    protected void interrupted() {
        //driveTrain.stop();
    }
}
</pre></body></html>