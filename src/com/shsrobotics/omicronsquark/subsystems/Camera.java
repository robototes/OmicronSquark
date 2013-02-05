package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.sun.cldc.jna.Pointer;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Camera extends Subsystem implements Maps {
    private AxisCamera camera = AxisCamera.getInstance();
    final double topGoalAspectRatio = 46.0 / 133.0;
    final double lowGoalAspectRatio = 32.2 / 37.0;
    double tolerance = 0.2;
    
    private double inverseNormalizedDistance = Math.tan(Math.toRadians(Constants.cameraHorizontalViewAngle / 2));


    public Camera() {
        camera.writeResolution(AxisCamera.ResolutionT.k160x120);
    }

    public double getAlignmentAngle() {
        double angle = 0;
        try {
            ColorImage color = camera.getImage();
            //int hueLow = (int) SmartDashboard.getNumber("Hue Low", 60);
            //int hueHigh = (int) SmartDashboard.getNumber("Hue High", 60);
            //BinaryImage white = color.thresholdHSL(hueLow, hueHigh, 25, 255, 15, 255); 
            BinaryImage white = color.thresholdHSL(52, 100, 0, 255, 0, 255); 
            white = white.convexHull(true);            
            
            white = white.removeSmallObjects(true, 2);
            
            color.write("CurrentColorImage.jpeg");
            color.free();
            
            ParticleAnalysisReport[] particles = white.getOrderedParticleAnalysisReports(); // get particles
            white.free();
            
            double maxHeight = -2;
            int topGoalIndex = -1;
            int[] goalTypes = null;
            for (int i = 0; i < particles.length; i++) {
                ParticleAnalysisReport goal = particles[i];
                if (failsRectangularityTest(goal)) continue;
                int aspectRatio = testAspectRatio(color.image, goal, i);
                goalTypes[i] = aspectRatio;
                color.free();
                if (aspectRatio == Constants.failsAspectRatioTest) continue;  
                double y = goal.center_mass_y_normalized;
                if (y > maxHeight) {
                    maxHeight = y;
                    topGoalIndex = i;
                }
            }
            if (topGoalIndex == -1) {
                angle = Double.NEGATIVE_INFINITY;
            } else {
                angle = Math.toDegrees(MathUtils.atan(particles[topGoalIndex].center_mass_x_normalized * inverseNormalizedDistance));
            }
            System.out.println("Of " + particles.length + " particles, the horizontal angle to the highest one is " + angle + " degrees.");
            SmartDashboard.putNumber("Horizontal Angle to Target", angle);
            if (angle == Double.NEGATIVE_INFINITY) return angle;
            switch (goalTypes[topGoalIndex]) {
                case Constants.lowGoal:
                    SmartDashboard.putString("Goal Type", "Low Goal");
                    break;
                case Constants.middleGoal:
                    SmartDashboard.putString("Goal Type", "Middle Goal");
                    break;
                case Constants.highGoal:
                    SmartDashboard.putString("Goal Type", "High Goal");
                    break;
            }
        } catch (AxisCameraException ex) {
        } catch (NIVisionException ex) { }
        return angle;
    }
    
    private boolean failsRectangularityTest(ParticleAnalysisReport goal) {
        double area = goal.particleArea;
        double expectedArea = goal.boundingRectHeight * goal.boundingRectWidth;
        double areaError = Math.abs(expectedArea - area) / area;
        if (areaError > Constants.significanceLevel_Percent / 100) { // if actual area isn't within a set percent of expected area
            return true;
        } else { 
            return false;
        }
    }
    
    private int testAspectRatio(Pointer image, ParticleAnalysisReport goal, int index) throws NIVisionException {
        double area = goal.particleArea;
        double perimeter = NIVision.MeasureParticle(image, index, true, NIVision.MeasurementType.IMAQ_MT_PERIMETER);
        double equivHeight = (perimeter + Math.sqrt(perimeter * perimeter - 16 * area)) / 4;
        double equivWidth = area / equivHeight;
        double equivAspectRatio = equivWidth / equivHeight;
        double[] errors = { // low, middle, high
            Math.abs(Constants.aspectRatios.lowGoal - equivAspectRatio) / equivAspectRatio,
            Math.abs(Constants.aspectRatios.middleGoal - equivAspectRatio) / equivAspectRatio,
            Math.abs(Constants.aspectRatios.highGoal - equivAspectRatio) / equivAspectRatio
        };
        double minError = 1;
        int minErrorIndex = -1;
        for (int i = 0; i < 3; i++) {
            if (errors[i] < minError) {
                minError = errors[i];
                minErrorIndex = i;
            }
        }
        if (errors[minErrorIndex] > Constants.significanceLevel_Percent) {
            return Constants.failsAspectRatioTest;
        } else {
            return minErrorIndex + 1;
        }
    }


    public void initDefaultCommand() { }
}
