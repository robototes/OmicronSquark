package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.CheckShootingDistance;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Camera extends Subsystem implements Maps {
    
    private AxisCamera camera = AxisCamera.getInstance();
    private CriteriaCollection criteria = new CriteriaCollection();
    
    private double inverseNormalizedDistanceHorizontal = Math.tan(Math.toRadians(Constants.cameraHorizontalViewAngle / 2));
    private double inverseNormalizedDistanceVertical = Math.tan(Math.toRadians(Constants.cameraVerticalViewAngle / 2));

    public Camera() {
        camera.writeResolution(AxisCamera.ResolutionT.k160x120);
        criteria.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, 1000, 19200, false);
    }

    public Angles getAlignmentAngles() {
        double horizontalAngle = 0;
        double verticalAngle = 0;
		Angles angles;
        BinaryImage white;
        try {
            white = getFilteredImage();    
			if (white == null) {
				return new Angles(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
			}
            ParticleAnalysisReport[] particles = white.getOrderedParticleAnalysisReports(); // get particles            
            double maxHeight = -2;
            int topGoalIndex = -1;
            int[] goalTypes = new int[particles.length];
            for (int i = 0; i < particles.length; i++) {
                ParticleAnalysisReport goal = particles[i];
                if (failsRectangularityTest(goal)) {
					continue;
				}
                int aspectRatio = testAspectRatio(white, goal, i);
                goalTypes[i] = aspectRatio;
                if (aspectRatio == Constants.failsAspectRatioTest) {
					continue;
				}
                double y = goal.center_mass_y_normalized;
                if (y > maxHeight) {
                    maxHeight = y;
                    topGoalIndex = i;
                }
            }
            
            white.free();
            if (topGoalIndex == -1) { // not found
                horizontalAngle = Double.NEGATIVE_INFINITY;
                verticalAngle = Double.NEGATIVE_INFINITY;
				SmartDashboard.putString("Goal Type", "NO GOAL");
            } else {
                horizontalAngle = Math.toDegrees(MathUtils.atan(particles[topGoalIndex].center_mass_x_normalized * inverseNormalizedDistanceHorizontal));
                verticalAngle = Math.toDegrees(MathUtils.atan(particles[topGoalIndex].center_mass_y_normalized * inverseNormalizedDistanceVertical));
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
            }
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
        
		angles = new Angles(horizontalAngle, verticalAngle);				
        return angles;
    }	
	
    
    public boolean goalInView() {
        try {
            BinaryImage filtered = getFilteredImage();
			if (filtered == null) {
				return false;
			}
            if (filtered.getNumberParticles() > 0 && getAlignmentAngles().horizontal != Double.NEGATIVE_INFINITY) {   // goal found             
                return true;
            } else {
                return false;
            }
        } catch (NIVisionException ex) { }
        return false;
    }
    
    private boolean failsRectangularityTest(ParticleAnalysisReport goal) {
        double area = goal.particleArea;
        double expectedArea = goal.boundingRectHeight * goal.boundingRectWidth;
        double areaError = Math.abs(expectedArea - area) / area;
        if (areaError > Constants.significanceLevel_Rectangularity / 100) { // if actual area isn't within a set percent of expected area
            return true;
        } else { 
            return false;
        }
    }
    
    private int testAspectRatio(BinaryImage image, ParticleAnalysisReport goal, int index) {
        double elongationFactor; 
        try {
            elongationFactor = NIVision.MeasureParticle(image.image, index, false, NIVision.MeasurementType.IMAQ_MT_ELONGATION_FACTOR);
        } catch (NIVisionException e) {
            elongationFactor = goal.boundingRectWidth / goal.boundingRectHeight;
        }
        double[] errors = { // low, middle, high
            Math.abs(Constants.aspectRatios.lowGoal - elongationFactor) / elongationFactor,
            Math.abs(Constants.aspectRatios.middleGoal - elongationFactor) / elongationFactor,
            Math.abs(Constants.aspectRatios.highGoal - elongationFactor) / elongationFactor
        };
        double minError = 10;
        int minErrorIndex = -1;
        for (int i = 0; i < 3; i++) {
            if (errors[i] < minError) {
                minError = errors[i];
                minErrorIndex = i;
            }
        }
        if (errors[minErrorIndex] > Constants.significanceLevel_Percent / 100) {
            return Constants.failsAspectRatioTest;
        } else {
            return minErrorIndex + 1;
        }
    }
    
    private BinaryImage getFilteredImage() {
        BinaryImage white = null;
        try {
            ColorImage color = camera.getImage();
            BinaryImage threshold = color.thresholdHSL(28, 255, 93, 200, 90, 205);
				color.free();
            BinaryImage convexHull = threshold.convexHull(false);
				threshold.free();
			white = convexHull.particleFilter(criteria);
				convexHull.free();
        } catch (AxisCameraException ex) {
			ex.printStackTrace();
        } catch (NIVisionException ex) {
			ex.printStackTrace();
		}
        return white;
    }
    
    public void initDefaultCommand() {
		setDefaultCommand(new CheckShootingDistance());
	}
	
	
	public static class Angles {		
		public final double horizontal;
		public final double vertical;
		
		public Angles(double horizontal, double vertical) {
			this.horizontal = horizontal;
			this.vertical = vertical;
		}
	}
}
