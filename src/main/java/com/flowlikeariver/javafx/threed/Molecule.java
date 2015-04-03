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

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.Node;

/**
 * MoleculeSampleApp
 */
public class Molecule extends Application {

static final double MODIFIER_FACTOR = 0.1;

double mousePosX;
double mousePosY;

private Group buildAxes() {
  final PhongMaterial redMaterial = new PhongMaterial();
  redMaterial.setDiffuseColor(Color.DARKRED);
  redMaterial.setSpecularColor(Color.RED);

  final PhongMaterial greenMaterial = new PhongMaterial();
  greenMaterial.setDiffuseColor(Color.DARKGREEN);
  greenMaterial.setSpecularColor(Color.GREEN);

  final PhongMaterial blueMaterial = new PhongMaterial();
  blueMaterial.setDiffuseColor(Color.DARKBLUE);
  blueMaterial.setSpecularColor(Color.BLUE);

  final Box xAxis = new Box(240.0, 1, 1);
  final Box yAxis = new Box(1, 240.0, 1);
  final Box zAxis = new Box(1, 1, 240.0);

  xAxis.setMaterial(redMaterial);
  yAxis.setMaterial(greenMaterial);
  zAxis.setMaterial(blueMaterial);

  Group axisGroup = new Group();
  axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
  return axisGroup;
}

private Xform createH(PhongMaterial sphereMaterial) {
  Sphere sphere = new Sphere(30.0);
  sphere.setMaterial(sphereMaterial);
  sphere.setTranslateX(0.0);
  Xform h = new Xform();
  h.add(sphere);
  h.setTx(100.0);
  return h;
}

private Cylinder createBond(PhongMaterial m) {
  Cylinder c = new Cylinder(5, 100);
  c.setMaterial(m);
  c.setTranslateX(50.0);
  c.setRotationAxis(Rotate.Z_AXIS);
  c.setRotate(90.0);
  return c;
}

// Molecule Hierarchy
// [*] moleculeXform
//     [*] oxygenXform
//         [*] oxygenSphere
//     [*] hydrogen1SideXform
//         [*] hydrogen1Xform
//             [*] hydrogen1Sphere
//         [*] bond1Cylinder
//     [*] hydrogen2SideXform
//         [*] hydrogen2Xform
//             [*] hydrogen2Sphere
//         [*] bond2Cylinder
private Xform buildMolecule() {
  final PhongMaterial redMaterial = new PhongMaterial();
  redMaterial.setDiffuseColor(Color.DARKRED);
  redMaterial.setSpecularColor(Color.RED);

  final PhongMaterial whiteMaterial = new PhongMaterial();
  whiteMaterial.setDiffuseColor(Color.WHITE);
  whiteMaterial.setSpecularColor(Color.LIGHTBLUE);

  final PhongMaterial greyMaterial = new PhongMaterial();
  greyMaterial.setDiffuseColor(Color.DARKGREY);
  greyMaterial.setSpecularColor(Color.GREY);

  Sphere oxygenSphere = new Sphere(40.0);
  oxygenSphere.setMaterial(redMaterial);

  Xform oxygenXform = new Xform();
  oxygenXform.add(oxygenSphere);

  Xform hydrogen1SideXform = new Xform();
  hydrogen1SideXform.add(createH(whiteMaterial)).add(createBond(greyMaterial));

  Xform hydrogen2SideXform = new Xform();
  hydrogen2SideXform.add(createH(whiteMaterial)).add(createBond(greyMaterial));
  hydrogen2SideXform.setRotateY(104.5);

  Xform moleculeXform = new Xform();
  moleculeXform.add(oxygenXform).add(hydrogen1SideXform).add(hydrogen2SideXform);
  Xform moleculeGroup = new Xform();
  moleculeGroup.add(moleculeXform);
  return moleculeGroup;
}

private void handleMouse(Scene scene, final Node root, Camera camera) {
  scene.setOnMousePressed(me -> {
    mousePosX = me.getSceneX();
    mousePosY = me.getSceneY();
  });
  scene.setOnMouseDragged(me -> {
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
  });
}

private void handleKeyboard(Scene scene, Camera camera, Group axes, Group molecule) {
  scene.setOnKeyPressed(event -> {
    switch (event.getCode()) {
      case X:
        if (event.isControlDown()) {
          axes.setVisible(!axes.isVisible());
        }
        break;
      case S:
        if (event.isControlDown()) {
          molecule.setVisible(!molecule.isVisible());
        }
        break;
      default:
        camera.handleKeyboard(event);
        break;
    }
  });
}

@Override
public void start(Stage primaryStage) {
  Group root = new Group();
  Xform world = new Xform();
  Camera camera = new Camera(root);
  Group axes = buildAxes();
  world.add(axes);
  Xform molecule = buildMolecule();
  world.add(molecule);
  root.getChildren().add(world);

  Scene scene = new Scene(root, 1024, 768, true);
  scene.setFill(Color.GREY);
  handleKeyboard(scene, camera, axes, molecule);
  handleMouse(scene, world, camera);

  scene.setCamera(camera.getPerspectiveCamera());

  primaryStage.setTitle("Molecule Sample Application");
  primaryStage.setScene(scene);
  primaryStage.show();
}

/**
 * The main() method is ignored in correctly deployed JavaFX application. main()
 * serves only as fallback in case the application can not be launched through
 * deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores
 * main().
 *
 * @param args the command line arguments
 */
public static void main(String[] args) {
  System.setProperty("prism.dirtyopts", "false");
  launch(args);
}
}
