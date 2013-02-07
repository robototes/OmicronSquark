package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
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
            BinaryImage white = color.thresholdHSL(60, 240, 20, 255, 20, 255);
            white = white.convexHull(true);    
            ParticleAnalysisReport[] particles = white.getOrderedParticleAnalysisReports(); // get particles
            double maxHeight = -2;
            int topGoalIndex = -1;
            int[] goalTypes = new int[particles.length];
            for (int i = 0; i < particles.length; i++) {
                ParticleAnalysisReport goal = particles[i];
                if (failsRectangularityTest(goal)) continue;
                System.out.println("passed rectangularity test");
                int aspectRatio = testAspectRatio(white, goal, i);  
                goalTypes[i] = aspectRatio;
                color.free();
                white.free();            
                if (aspectRatio == Constants.failsAspectRatioTest) continue;
                System.out.println("passed aspect ratio test");
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
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
        return angle;
    }
    
    private boolean failsRectangularityTest(ParticleAnalysisReport goal) {
        double area = goal.particleArea;
        double expectedArea = goal.boundingRectHeight * goal.boundingRectWidth;
        double areaError = Math.abs(expectedArea - area) / area;
        System.out.println(areaError);
        if (areaError > Constants.significanceLevel_Rectangularity / 100) { // if actual area isn't within a set percent of expected area
            return true;
        } else { 
            return false;
        }
    }
    
    private int testAspectRatio(BinaryImage image, ParticleAnalysisReport goal, int index) throws NIVisionException {
        double equivHeight = NIVision.MeasureParticle(image.image, index, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);   
        double equivWidth = NIVision.MeasureParticle(image.image, index, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
        double equivAspectRatio = equivWidth / equivHeight;
        double[] errors = { // low, middle, high
            Math.abs(Constants.aspectRatios.lowGoal - equivAspectRatio) / equivAspectRatio,
            Math.abs(Constants.aspectRatios.middleGoal - equivAspectRatio) / equivAspectRatio,
            Math.abs(Constants.aspectRatios.highGoal - equivAspectRatio) / equivAspectRatio
        };
        System.out.println(errors[0]);
        System.out.println(errors[1]);
        System.out.println(errors[2]);
        System.out.println(equivHeight);
        System.out.println(equivWidth);
        System.out.println(equivAspectRatio);
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
