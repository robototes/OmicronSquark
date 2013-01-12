package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Camera extends Subsystem implements Maps {
    private AxisCamera camera = AxisCamera.getInstance();

    private double inverseNormalizedDistance = Math.tan(Math.toRadians(Constants.cameraHorizontalViewAngle / 2));


    public Camera() {
        camera.writeResolution(AxisCamera.ResolutionT.k320x240);
    }

    public double getAlignmentAngle() {
        double angle = 0;
        try {
            ColorImage color = camera.getImage();
            BinaryImage white = color.thresholdHSL(80, 240, 25, 255, 15, 255);
            white = white.convexHull(false);
            white = white.convexHull(false);
            white = white.removeSmallObjects(true, 2);
            color.write("CurrentColorImage.jpeg");
            ParticleAnalysisReport[] particles = white.getOrderedParticleAnalysisReports(); // get the five goals
            white.free();
            color.free();
            double maxHeight = -2;
            int topGoalIndex = -1;
            for (int i = 0; i < particles.length; i++) {
                ParticleAnalysisReport goal = particles[i];
                double area = goal.particleArea;
                double expectedArea = goal.boundingRectHeight * goal.boundingRectWidth;
                double sidesRatio = goal.boundingRectWidth / goal.boundingRectHeight;
                double areaError = Math.abs(expectedArea - area) / area;
                System.out.println("Particle " + i +", Area error: " + areaError);
                System.out.println("Particle " + i +", Aspect Ratio: " + sidesRatio);
                if (areaError > Constants.significanceLevel_Percent / 100) continue; // if actual area isn't within a set percent of expected area
                if ((1 - Constants.significanceLevel_Percent) * Constants.minAspectRatio
                        < sidesRatio && sidesRatio
                        < (1 + Constants.significanceLevel_Percent) * Constants.maxAspectRatio) continue; // too thin or too fat
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
        } catch (AxisCameraException ex) {
        } catch (NIVisionException ex) { }
        return angle;
    }

    public void initDefaultCommand() { }
}
