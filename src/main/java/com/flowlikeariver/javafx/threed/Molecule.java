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
import javafx.animation.Timeline;
import javafx.scene.Node;

/**
 * MoleculeSampleApp
 */
public class Molecule extends Application {

final Group root = new Group();
final Group axisGroup = new Group();
final Xform world = new Xform();
final Xform moleculeGroup = new Xform();
private Timeline timeline;
boolean timelinePlaying = false;
double mousePosX;
double mousePosY;
double mouseDeltaX;
double mouseDeltaY;

private void buildScene() {
  root.getChildren().add(world);
}

private void buildAxes() {
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

  axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
  world.add(axisGroup);
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
private void buildMolecule() {
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

  Sphere hydrogen1Sphere = new Sphere(30.0);
  hydrogen1Sphere.setMaterial(whiteMaterial);
  hydrogen1Sphere.setTranslateX(0.0);

  Sphere hydrogen2Sphere = new Sphere(30.0);
  hydrogen2Sphere.setMaterial(whiteMaterial);
  hydrogen2Sphere.setTranslateZ(0.0);

  Cylinder bond1Cylinder = new Cylinder(5, 100);
  bond1Cylinder.setMaterial(greyMaterial);
  bond1Cylinder.setTranslateX(50.0);
  bond1Cylinder.setRotationAxis(Rotate.Z_AXIS);
  bond1Cylinder.setRotate(90.0);

  Xform oxygenXform = new Xform();
  oxygenXform.add(oxygenSphere);

  Xform hydrogen1Xform = new Xform();
  hydrogen1Xform.add(hydrogen1Sphere);
  hydrogen1Xform.setTx(100.0);
  Xform hydrogen1SideXform = new Xform();
  hydrogen1SideXform.add(hydrogen1Xform).add(bond1Cylinder);

  Cylinder bond2Cylinder = new Cylinder(5, 100);
  bond2Cylinder.setMaterial(greyMaterial);
  bond2Cylinder.setTranslateX(50.0);
  bond2Cylinder.setRotationAxis(Rotate.Z_AXIS);
  bond2Cylinder.setRotate(90.0);

  Xform hydrogen2Xform = new Xform();
  hydrogen2Xform.add(hydrogen2Sphere);
  hydrogen2Xform.setTx(100.0);
  Xform hydrogen2SideXform = new Xform();
  hydrogen2SideXform.add(hydrogen2Xform).add(bond2Cylinder);
  hydrogen2SideXform.setRotateY(104.5);

  Xform moleculeXform = new Xform();
  moleculeXform.add(oxygenXform).add(hydrogen1SideXform).add(hydrogen2SideXform);
  moleculeGroup.add(moleculeXform);
  world.add(moleculeGroup);
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
    mouseDeltaX = (mousePosX - mouseOldX);
    mouseDeltaY = (mousePosY - mouseOldY);

    double modifier = 1.0;
    double modifierFactor = 0.1;

    if (me.isControlDown()) {
      modifier = 0.1;
    }
    if (me.isShiftDown()) {
      modifier = 10.0;
    }
    if (me.isPrimaryButtonDown()) {
      camera.getXform1()
        .adjustRy(-mouseDeltaX * modifierFactor * modifier * 2.0)
        .adjustRx(mouseDeltaY * modifierFactor * modifier * 2.0);
    }
    else if (me.isSecondaryButtonDown()) {
      camera.adjustCameraZ(mouseDeltaX * modifierFactor * modifier);
    }
    else if (me.isMiddleButtonDown()) {
      camera.getXform2()
        .adjustTx(mouseDeltaX * modifierFactor * modifier * 0.3)
        .adjustTy(mouseDeltaY * modifierFactor * modifier * 0.3);
    }
  });
}

private void handleKeyboard(Scene scene, Camera camera) {
  scene.setOnKeyPressed(event -> {
    switch (event.getCode()) {
      case X:
        if (event.isControlDown()) {
          axisGroup.setVisible(!axisGroup.isVisible());
        }
        break;
      case S:
        if (event.isControlDown()) {
          moleculeGroup.setVisible(!moleculeGroup.isVisible());
        }
        break;
      case SPACE:
        if (timelinePlaying) {
          timeline.pause();
        }
        else {
          timeline.play();
        }
        timelinePlaying = !timelinePlaying;
        break;
      default:
        camera.handleKeyboard(event);
        break;
    }
  });
}

@Override
public void start(Stage primaryStage) {
  buildScene();
  Camera camera = new Camera(root);
  buildAxes();
  buildMolecule();

  Scene scene = new Scene(root, 1024, 768, true);
  scene.setFill(Color.GREY);
  handleKeyboard(scene, camera);
  handleMouse(scene, world, camera);

  primaryStage.setTitle("Molecule Sample Application");
  primaryStage.setScene(scene);
  primaryStage.show();

  scene.setCamera(camera.getPerspectiveCamera());
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
