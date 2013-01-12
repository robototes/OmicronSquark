package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Timer;

public class AbsoluteGyroscope extends Gyro implements Maps {
    
    private double distance;
    private double lastTime;
    private Timer timer = new Timer();
    
    public AbsoluteGyroscope(int port) {
        super(port);
        distance = 0;
        lastTime = 0;
    }    
    
    public void reset() {
        super.reset();
        distance = 0;
        lastTime = 0;
        timer.reset();
        timer.start();
    }
    
    public double getAngularVelocity() {
        return super.getAngle();
    }
    
    public double getAngle() {
        double velocity = getAngularVelocity();
        double currentTime = timer.get() / 1e6;
        double time = currentTime - lastTime;
        distance += velocity * time;
        lastTime = currentTime;
        return distance;
    }
}
