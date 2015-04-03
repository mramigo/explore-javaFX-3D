package com.flowlikeariver.javafx.threed;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;

public class Camera {

public static final double DELTA_MULTIPLIER = 200.0;
public static final double CONTROL_MULTIPLIER = 0.1;
public static final double SHIFT_MULTIPLIER = 0.1;
public static final double ALT_MULTIPLIER = 0.5;

private final PerspectiveCamera pc = new PerspectiveCamera(true);
private final Xform xForm1 = new Xform();
private final Xform xForm2 = new Xform();
private final Xform xform3 = new Xform();
private final double cameraDistance = 450;

public Camera(Group root) {
  xform3.add(pc);
  xform3.setRotateZ(180.0);
  xForm2.add(xform3);
  xForm1.add(xForm2);
  root.getChildren().add(xForm1);

  pc.setNearClip(0.1);
  pc.setFarClip(10000.0);
  pc.setTranslateZ(-cameraDistance);
  xForm1.setRotateY(320.0);
  xForm1.setRotateX(40);
}

/**
 * @return the camera
 */
public PerspectiveCamera getPerspectiveCamera() {
  return pc;
}

/**
 * @return the cameraXform
 */
public Xform getXform1() {
  return xForm1;
}

/**
 * @return the cameraXform2
 */
public Xform getXform2() {
  return xForm2;
}

public void adjustCameraZ(double delta) {
  pc.setTranslateZ(pc.getTranslateZ() + delta);
}

}
