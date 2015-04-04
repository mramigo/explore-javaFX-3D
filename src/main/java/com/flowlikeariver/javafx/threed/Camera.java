package com.flowlikeariver.javafx.threed;

import javafx.scene.PerspectiveCamera;

public class Camera {
public static final double DELTA_MULTIPLIER = 200;

private final PerspectiveCamera pc = new PerspectiveCamera(true);
private final Xform xform3 = new Xform(pc);
private final Xform xForm2 = new Xform(xform3);
private final Xform xForm1 = new Xform(xForm2);
private final double cameraDistance = 450;

public Camera() {
  pc.setNearClip(0.1);
  pc.setFarClip(10000);
  pc.setTranslateZ(-cameraDistance);
  xForm1.setRotateY(320);
  xForm1.setRotateX(40);
  xform3.setRotateZ(180);
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

public Xform adjustTx(double delta) {
  return xForm2.adjustTx(delta);
}

public Xform adjustTy(double delta) {
  return xForm2.adjustTy(delta);
}

public Xform setTx(double delta) {
  return xForm2.setTx(delta);
}

public Xform setTy(double delta) {
  return xForm2.setTy(delta);
}

public Xform adjustRx(double delta) {
  return xForm1.adjustRx(delta);
}

public Xform adjustRy(double delta) {
  return xForm1.adjustRy(delta);
}

public Xform setRx(double delta) {
  return xForm1.setRx(delta);
}

public Xform setRy(double delta) {
  return xForm1.setRy(delta);
}

}
