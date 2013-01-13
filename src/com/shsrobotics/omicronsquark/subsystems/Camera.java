package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

public class Camera extends Subsystem implements Maps {
	private AxisCamera camera = AxisCamera.getInstance();
	private CriteriaCollection criteria = new CriteriaCollection();

    private static Servo rotateHorizontal = new Servo(Robot.Camera.horizontalServo);
    private static Servo rotateVertical = new Servo(Robot.Camera.verticalServo);

    private double inverseNormalizedDistance = Math.tan(Constants.cameraHorizontalViewAngle / 2);

	public Camera() {
		criteria.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, 500, 65535, false); // eliminate squares too big or too small to be goals.
		criteria.addCriteria(NIVision.MeasurementType.IMAQ_MT_CENTER_OF_MASS_Y, 0, 1, true); // upper half of image
	}

    public double getAlignmentAngle() {
		double angle = 0;
		try {
			ColorImage color = camera.getImage();
			BinaryImage white = color.thresholdHSL(0, 360, 0, 10, 70, 100);
			white = white.convexHull(false);
			white = white.particleFilter(criteria);
			ParticleAnalysisReport[] particles = white.getOrderedParticleAnalysisReports(); // get the five goals
			white.free();
			color.free();
			double maxHeight = -1;
			int topGoalIndex = -1;
			for (int i = 0; i < particles.length; i++) {
				ParticleAnalysisReport goal = particles[i];
				double area = goal.particleArea;
				double expectedArea = goal.boundingRectHeight * goal.boundingRectWidth;
				double sidesRatio = goal.boundingRectWidth / goal.boundingRectHeight;
				if (expectedArea - area > Constants.significanceLevel_Percent / 100) continue; // if actual area isn't within 5 percent of expected area
				if (sidesRatio - Constants.tallGoalsWidth / Constants.mediumGoalHeight < -Constants.significanceLevel_Percent) continue; // too thin
				if (sidesRatio - Constants.tallGoalsWidth / Constants.tallestGoalHeight > Constants.significanceLevel_Percent) continue; // too fat
				double y = goal.center_mass_y_normalized;
				if (y > maxHeight) {
					maxHeight = y;
					topGoalIndex = i;
				}
			}
			angle = MathUtils.atan(particles[topGoalIndex].center_mass_x_normalized * inverseNormalizedDistance);
		} catch (AxisCameraException ex) {
		} catch (NIVisionException ex) { }
		return angle;
    }

    public void rotateHorizontal(double angle) {
        rotateHorizontal.setAngle(angle);
    }

    public void rotateVertical(double angle) {
        rotateVertical.setAngle(angle);
    }

    public void joystickChange(double horizontalChange, double verticalChange) {
        rotateHorizontal.setAngle(rotateHorizontal.getAngle() + horizontalChange);
        rotateVertical.setAngle(rotateVertical.getAngle() + verticalChange);
    }

    public void initDefaultCommand() { }
}
