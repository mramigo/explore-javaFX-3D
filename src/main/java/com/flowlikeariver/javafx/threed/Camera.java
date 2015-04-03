package com.flowlikeariver.javafx.threed;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import static javafx.scene.input.KeyCode.Z;
import javafx.scene.input.KeyEvent;

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

public void handleKeyboard(KeyEvent event) {
  System.out.println(event.getCode());
  switch (event.getCode()) {
    case Z:
      if (event.isShiftDown()) {
        xForm1.setRy(0.0).setRx(0.0);
        pc.setTranslateZ(-300.0);
      }
      xForm2.setTx(0.0).setTy(0.0);
      break;
    case UP:
      if (event.isControlDown() && event.isShiftDown()) {
        xForm2.adjustTy(-10.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown() && event.isShiftDown()) {
        xForm1.adjustRx(-10.0 * ALT_MULTIPLIER);
      }
      else if (event.isControlDown()) {
        xForm2.adjustTy(-1.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown()) {
        xForm1.adjustRx(-2.0 * ALT_MULTIPLIER);
      }
      else if (event.isShiftDown()) {
        adjustCameraZ(5.0 * SHIFT_MULTIPLIER);
      }
      break;
    case DOWN:
      if (event.isControlDown() && event.isShiftDown()) {
        xForm2.adjustTy(10.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown() && event.isShiftDown()) {
        xForm1.adjustRx(10.0 * ALT_MULTIPLIER);
      }
      else if (event.isControlDown()) {
        xForm2.adjustTy(1.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown()) {
        xForm1.adjustRx(2.0 * ALT_MULTIPLIER);
      }
      else if (event.isShiftDown()) {
        adjustCameraZ(-5.0 * SHIFT_MULTIPLIER);
      }
      break;
    case RIGHT:
      if (event.isControlDown() && event.isShiftDown()) {
        xForm2.adjustTx(10.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown() && event.isShiftDown()) {
        xForm1.adjustRy(-10.0 * ALT_MULTIPLIER);
      }
      else if (event.isControlDown()) {
        xForm2.adjustTx(1.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown()) {
        xForm1.adjustRy(-2.0 * ALT_MULTIPLIER);
      }
      break;
    case LEFT:
      if (event.isControlDown() && event.isShiftDown()) {
        xForm2.adjustTx(-10.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown() && event.isShiftDown()) {
        xForm1.adjustRy(10.0 * ALT_MULTIPLIER);  // -
      }
      else if (event.isControlDown()) {
        xForm2.adjustTx(-1.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown()) {
        xForm1.adjustRy(2.0 * ALT_MULTIPLIER);  // -
      }
      break;
  }
}

}
