package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.*;

public class Camera extends Subsystem implements Maps {
	private AxisCamera camera = AxisCamera.getInstance();

private double inverseNormalizedDistance = Math.tan(Constants.cameraViewAngle / 2);

	public double getThrowingAngle() {
		double angle = 0;
		try {
			ColorImage color = camera.getImage();
			BinaryImage white = color.thresholdHSL(0, 360, 0, 10, 70, 100);
			white.convexHull(true);
			white.removeSmallObjects(true, 4);
			ParticleAnalysisReport[] particles = white.getOrderedParticleAnalysisReports(5); // get the five goals
			white.free();
			double maxHeight = -1;
			for (int i = 0; i < particles.length; i++) {
				double y = particles[i].center_mass_y_normalized;
				if (y > maxHeight) {
					maxHeight = y;
				}
			}
			angle = MathUtils.atan(maxHeight * inverseNormalizedDistance);
		} catch (AxisCameraException ex) {
		} catch (NIVisionException ex) { }
		return angle;
	}

	public void initDefaultCommand() { }
}
