package com.shsrobotics.omicronsquark.subsystems;

import com.shsrobotics.omicronsquark.Maps;
import com.shsrobotics.omicronsquark.commands.MoveCameraWithJoystick;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

public class Camera extends Subsystem implements Maps {
	private AxisCamera camera = AxisCamera.getInstance();

    private static Servo rotateHorizontal = new Servo(Robot.Camera.horizontalServo);
    private static Servo rotateVertical = new Servo(Robot.Camera.verticalServo);

    private double inverseNormalizedDistance = Math.tan(Constants.cameraViewAngle / 2);

    public double getAlignmentAngle() {
            double angle = 0;
            try {
                    ColorImage color = camera.getImage();
                    BinaryImage white = color.thresholdHSL(0, 360, 0, 10, 70, 100);
                    white.convexHull(true);
                    white.removeSmallObjects(true, 4);
                    ParticleAnalysisReport[] particles = white.getOrderedParticleAnalysisReports(5); // get the five goals
                    white.free();
                    double maxHeight = -1;
                    int topGoalIndex = -1;
                    for (int i = 0; i < particles.length; i++) {
                            double y = particles[i].center_mass_y_normalized;
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

    public void initDefaultCommand() {
        setDefaultCommand(new MoveCameraWithJoystick());
    }
}
