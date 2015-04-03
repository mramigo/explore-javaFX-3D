package com.flowlikeariver.javafx.threed;

import javafx.scene.input.KeyEvent;

public class KeyboardHandler {

public static final double CONTROL_MULTIPLIER = 0.1;
public static final double SHIFT_MULTIPLIER = 0.1;
public static final double ALT_MULTIPLIER = 0.5;

private final Camera camera;

public KeyboardHandler(Camera camera) {
  this.camera = camera;
}

public void handleKeyboard(KeyEvent event) {
  switch (event.getCode()) {
    case Z:
      if (event.isShiftDown()) {
        camera.getXform1().setRy(0.0).setRx(0.0);
        camera.getPerspectiveCamera().setTranslateZ(-300.0);
      }
      camera.getXform2().setTx(0.0).setTy(0.0);
      break;
    case UP:
      if (event.isControlDown() && event.isShiftDown()) {
        camera.getXform2().adjustTy(-10.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown() && event.isShiftDown()) {
        camera.getXform1().adjustRx(-10.0 * ALT_MULTIPLIER);
      }
      else if (event.isControlDown()) {
        camera.getXform2().adjustTy(-1.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown()) {
        camera.getXform1().adjustRx(-2.0 * ALT_MULTIPLIER);
      }
      else if (event.isShiftDown()) {
        camera.adjustCameraZ(5.0 * SHIFT_MULTIPLIER);
      }
      break;
    case DOWN:
      if (event.isControlDown() && event.isShiftDown()) {
        camera.getXform2().adjustTy(10.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown() && event.isShiftDown()) {
        camera.getXform1().adjustRx(10.0 * ALT_MULTIPLIER);
      }
      else if (event.isControlDown()) {
        camera.getXform2().adjustTy(1.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown()) {
        camera.getXform1().adjustRx(2.0 * ALT_MULTIPLIER);
      }
      else if (event.isShiftDown()) {
        camera.adjustCameraZ(-5.0 * SHIFT_MULTIPLIER);
      }
      break;
    case RIGHT:
      if (event.isControlDown() && event.isShiftDown()) {
        camera.getXform2().adjustTx(10.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown() && event.isShiftDown()) {
        camera.getXform1().adjustRy(-10.0 * ALT_MULTIPLIER);
      }
      else if (event.isControlDown()) {
        camera.getXform2().adjustTx(1.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown()) {
        camera.getXform1().adjustRy(-2.0 * ALT_MULTIPLIER);
      }
      break;
    case LEFT:
      if (event.isControlDown() && event.isShiftDown()) {
        camera.getXform2().adjustTx(-10.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown() && event.isShiftDown()) {
        camera.getXform1().adjustRy(10.0 * ALT_MULTIPLIER);  // -
      }
      else if (event.isControlDown()) {
        camera.getXform2().adjustTx(-1.0 * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown()) {
        camera.getXform1().adjustRy(2.0 * ALT_MULTIPLIER);  // -
      }
      break;
  }
}

}
