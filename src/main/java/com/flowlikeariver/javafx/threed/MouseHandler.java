package com.flowlikeariver.javafx.threed;

import javafx.scene.input.MouseEvent;

public class MouseHandler {

static final double MODIFIER_FACTOR = 0.1;

private double mousePosX;
private double mousePosY;

private final Camera camera;

public MouseHandler(Camera camera) {
  this.camera = camera;
}

public void recordMove(MouseEvent me) {
  mousePosX = me.getSceneX();
  mousePosY = me.getSceneY();
}

public void handleMouse(MouseEvent me) {
  double mouseOldX = mousePosX;
  double mouseOldY = mousePosY;
  mousePosX = me.getSceneX();
  mousePosY = me.getSceneY();

  double modifier = me.isShiftDown() ? 10.0 : (me.isControlDown() ? 0.1 : 1.0);
  double mouseDeltaX = (mousePosX - mouseOldX) * MODIFIER_FACTOR * modifier;
  double mouseDeltaY = (mousePosY - mouseOldY) * MODIFIER_FACTOR * modifier;
  if (me.isPrimaryButtonDown()) {
    camera.getXform1()
      .adjustRy(-mouseDeltaX * 2.0)
      .adjustRx(mouseDeltaY * 2.0);
  }
  else if (me.isSecondaryButtonDown()) {
    camera.adjustCameraZ(mouseDeltaX);
  }
  else if (me.isMiddleButtonDown()) {
    camera.getXform2()
      .adjustTx(mouseDeltaX * 0.3)
      .adjustTy(mouseDeltaY * 0.3);
  }
}

}
