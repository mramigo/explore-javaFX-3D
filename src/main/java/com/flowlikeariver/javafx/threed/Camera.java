package com.flowlikeariver.javafx.threed;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;

public class Camera {

private final PerspectiveCamera camera = new PerspectiveCamera(true);
private final Xform cameraXform = new Xform();
private final Xform cameraXform2 = new Xform();
private final Xform cameraXform3 = new Xform();
private final double cameraDistance = 450;

public Camera(Group root) {
  root.getChildren().add(cameraXform);
  cameraXform.add(cameraXform2);
  cameraXform2.add(cameraXform3);
  cameraXform3.add(camera);
  cameraXform3.setRotateZ(180.0);

  camera.setNearClip(0.1);
  camera.setFarClip(10000.0);
  camera.setTranslateZ(-cameraDistance);
  cameraXform.ry.setAngle(320.0);
  cameraXform.rx.setAngle(40);
}

/**
 * @return the camera
 */
public PerspectiveCamera getPerspectiveCamera() {
  return camera;
}

/**
 * @return the cameraXform
 */
public Xform getCameraXform() {
  return cameraXform;
}

/**
 * @return the cameraXform2
 */
public Xform getCameraXform2() {
  return cameraXform2;
}

}
