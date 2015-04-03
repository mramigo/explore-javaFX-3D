package com.flowlikeariver.javafx.threed;

import javafx.scene.PerspectiveCamera;

public class Camera {
public static final double DELTA_MULTIPLIER = 200.0;

private final PerspectiveCamera pc = new PerspectiveCamera(true);
private final Xform xform3 = new Xform(pc);
private final Xform xForm2 = new Xform(xform3);
private final Xform xForm1 = new Xform(xForm2);
private final double cameraDistance = 450;

public Camera() {
  xform3.setRotateZ(180.0);
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
