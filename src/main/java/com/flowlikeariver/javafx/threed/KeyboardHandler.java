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
        camera.setRy(0.0).setRx(0.0);
        camera.getPerspectiveCamera().setTranslateZ(-300.0);
      }
      camera.setTx(0.0).setTy(0.0);
      break;
    case UP:
      if (event.isControlDown()) {
        camera.adjustTy((event.isShiftDown() ? -10 : -1) * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown() && event.isShiftDown()) {
        camera.adjustRx((event.isShiftDown() ? -10 : -2) * ALT_MULTIPLIER);
      }
      else if (event.isShiftDown()) {
        camera.adjustCameraZ(5.0 * SHIFT_MULTIPLIER);
      }
      break;
    case DOWN:
      if (event.isControlDown()) {
        camera.adjustTy((event.isShiftDown() ? 10 : 1) * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown()) {
        camera.adjustRx((event.isShiftDown() ? 10 : 2) * ALT_MULTIPLIER);
      }
      else if (event.isShiftDown()) {
        camera.adjustCameraZ(-5.0 * SHIFT_MULTIPLIER);
      }
      break;
    case RIGHT:
      if (event.isControlDown()) {
        camera.adjustTx((event.isShiftDown() ? 10 : 1) * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown()) {
        camera.adjustRy((event.isShiftDown() ? -10 : -2) * ALT_MULTIPLIER);
      }
      break;
    case LEFT:
      if (event.isControlDown()) {
        camera.adjustTx((event.isShiftDown() ? -10 : -1) * CONTROL_MULTIPLIER);
      }
      else if (event.isAltDown()) {
        camera.adjustRy((event.isShiftDown() ? 10 : 2) * ALT_MULTIPLIER);  // -
      }
      break;
  }
}

}
