/*
 * Copyright (c) 2011, 2013 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/* Modifications licensed under the Eclipse Public License */
package com.flowlikeariver.javafx.threed;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class Xform extends Group {

public enum RotateOrder {

XYZ, XZY, YXZ, YZX, ZXY, ZYX
}

private Translate t = new Translate();
public Translate p = new Translate();
public Translate ip = new Translate();

private Rotate rx = new Rotate();
private Rotate ry = new Rotate();
private Rotate rz = new Rotate();

public Scale s = new Scale();

public Xform() {
  super();
  init();
  getTransforms().addAll(t, rz, ry, rx, s);
}

public Xform(RotateOrder rotateOrder) {
  super();
  init();
  // choose the order of rotations based on the rotateOrder
  switch (rotateOrder) {
    case XYZ:
      getTransforms().addAll(t, p, rz, ry, rx, s, ip);
      break;
    case XZY:
      getTransforms().addAll(t, p, ry, rz, rx, s, ip);
      break;
    case YXZ:
      getTransforms().addAll(t, p, rz, rx, ry, s, ip);
      break;
    case YZX:
      getTransforms().addAll(t, p, rx, rz, ry, s, ip);  // For Camera
      break;
    case ZXY:
      getTransforms().addAll(t, p, ry, rx, rz, s, ip);
      break;
    case ZYX:
      getTransforms().addAll(t, p, rx, ry, rz, s, ip);
      break;
  }
}

public Xform(Node... nodes) {
  this();
  getChildren().addAll(nodes);
}

private void init() {
  rx.setAxis(Rotate.X_AXIS);
  rz.setAxis(Rotate.Z_AXIS);
  ry.setAxis(Rotate.Y_AXIS);
}

public void setTranslate(double x, double y, double z) {
  t.setX(x);
  t.setY(y);
  t.setZ(z);
}

public void setTranslate(double x, double y) {
  t.setX(x);
  t.setY(y);
}

// Cannot override these methods as they are final:
// public void setTranslateX(double x) { t.setX(x); }
// public void setTranslateY(double y) { t.setY(y); }
// public void setTranslateZ(double z) { t.setZ(z); }
// Use these methods instead:
public Xform setTx(double x) {
  t.setX(x);
  return this;
}

public Xform adjustTx(double delta) {
  t.setX(t.getX() + delta);
  return this;
}

public Xform setTy(double y) {
  t.setY(y);
  return this;
}

public Xform adjustTy(double delta) {
  t.setY(t.getY() + delta);
  return this;
}

public Xform setTz(double z) {
  t.setZ(z);
  return this;
}

public Xform adjustTz(double delta) {
  t.setZ(t.getX() + delta);
  return this;
}

public void setRotate(double x, double y, double z) {
  rx.setAngle(x);
  ry.setAngle(y);
  rz.setAngle(z);
}

public void setRotateX(double x) {
  rx.setAngle(x);
}

public void setRotateY(double y) {
  ry.setAngle(y);
}

public void setRotateZ(double z) {
  rz.setAngle(z);
}

public Xform setRx(double x) {
  rx.setAngle(x);
  return this;
}

public Xform adjustRx(double delta) {
  rx.setAngle(rx.getAngle() + delta);
  return this;
}

public Xform setRy(double y) {
  ry.setAngle(y);
  return this;
}

public Xform adjustRy(double delta) {
  ry.setAngle(ry.getAngle() + delta);
  return this;
}

public Xform setRz(double z) {
  rz.setAngle(z);
  return this;
}

public Xform adjustRz(double delta) {
  rz.setAngle(rz.getAngle() + delta);
  return this;
}

public void setScale(double scaleFactor) {
  s.setX(scaleFactor);
  s.setY(scaleFactor);
  s.setZ(scaleFactor);
}

public void setScale(double x, double y, double z) {
  s.setX(x);
  s.setY(y);
  s.setZ(z);
}

    // Cannot override these methods as they are final:
// public void setScaleX(double x) { s.setX(x); }
// public void setScaleY(double y) { s.setY(y); }
// public void setScaleZ(double z) { s.setZ(z); }
// Use these methods instead:
public void setSx(double x) {
  s.setX(x);
}

public void setSy(double y) {
  s.setY(y);
}

public void setSz(double z) {
  s.setZ(z);
}

public void setPivot(double x, double y, double z) {
  p.setX(x);
  p.setY(y);
  p.setZ(z);
  ip.setX(-x);
  ip.setY(-y);
  ip.setZ(-z);
}

public void reset() {
  t.setX(0.0);
  t.setY(0.0);
  t.setZ(0.0);
  rx.setAngle(0.0);
  ry.setAngle(0.0);
  rz.setAngle(0.0);
  s.setX(1.0);
  s.setY(1.0);
  s.setZ(1.0);
  p.setX(0.0);
  p.setY(0.0);
  p.setZ(0.0);
  ip.setX(0.0);
  ip.setY(0.0);
  ip.setZ(0.0);
}

public void resetTSP() {
  t.setX(0.0);
  t.setY(0.0);
  t.setZ(0.0);
  s.setX(1.0);
  s.setY(1.0);
  s.setZ(1.0);
  p.setX(0.0);
  p.setY(0.0);
  p.setZ(0.0);
  ip.setX(0.0);
  ip.setY(0.0);
  ip.setZ(0.0);
}

// fluent add
public Xform add(Node node) {
  getChildren().add(node);
  return this;
}
}
