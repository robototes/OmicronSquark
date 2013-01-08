package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.CountDisks;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.*;

public class Camera extends Subsystem implements Maps {
	private AxisCamera camera = AxisCamera.getInstance();
	private boolean computing = false;
	private int numberOfDisks = 0;
	private ColorImage currentImage;

	public void getDiskCount() {
		ParticleAnalysisReport[] particles = null;
		computing = true;
		try {
			currentImage = camera.getImage();
			BinaryImage white = currentImage.thresholdHSL(0, 360, 0, 10, 70, 100);
			white.removeSmallObjects(true, 4);
			white.convexHull(true);
			particles = white.getOrderedParticleAnalysisReports();
			white.free();
		} catch (AxisCameraException ex) {
		} catch (NIVisionException ex) { }
		computing = false;
		numberOfDisks = particles.length;
	}

	public boolean isDoneComputing() {
		return !computing;
	}

	public int getResult() {
		return numberOfDisks;
	}

	public void free() {
		try {
			currentImage.free();
		} catch (NIVisionException ex) { }
	}

	public void initDefaultCommand() {
		setDefaultCommand(new CountDisks());
	}
}
