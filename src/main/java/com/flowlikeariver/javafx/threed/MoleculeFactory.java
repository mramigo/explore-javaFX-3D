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
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

/**
 * MoleculeSampleApp
 */
public class MoleculeFactory {

static final PhongMaterial redMaterial = new PhongMaterial();
static final PhongMaterial greenMaterial = new PhongMaterial();
static final PhongMaterial blueMaterial = new PhongMaterial();
static final PhongMaterial whiteMaterial = new PhongMaterial();
static final PhongMaterial greyMaterial = new PhongMaterial();

{
  redMaterial.setDiffuseColor(Color.DARKRED);
  redMaterial.setSpecularColor(Color.RED);
  greenMaterial.setDiffuseColor(Color.DARKGREEN);
  greenMaterial.setSpecularColor(Color.GREEN);
  blueMaterial.setDiffuseColor(Color.DARKBLUE);
  blueMaterial.setSpecularColor(Color.BLUE);
  greyMaterial.setDiffuseColor(Color.DARKGREY);
  greyMaterial.setSpecularColor(Color.GREY);
  whiteMaterial.setDiffuseColor(Color.WHITE);
  whiteMaterial.setSpecularColor(Color.LIGHTBLUE);
}

public static Group buildAxes() {
  Box xAxis = new Box(240.0, 1, 1);
  Box yAxis = new Box(1, 240.0, 1);
  Box zAxis = new Box(1, 1, 240.0);

  xAxis.setMaterial(redMaterial);
  yAxis.setMaterial(greenMaterial);
  zAxis.setMaterial(blueMaterial);

  Group axisGroup = new Group();
  axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
  return axisGroup;
}

private static Xform createH(PhongMaterial sphereMaterial) {
  Sphere sphere = new Sphere(30.0);
  sphere.setMaterial(sphereMaterial);
  sphere.setTranslateX(0.0);
  Xform h = new Xform(sphere);
  h.setTx(100.0);
  return h;
}

private static Cylinder createBond(PhongMaterial m) {
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
public static Xform buildMolecule() {
  Sphere oxygenSphere = new Sphere(40.0);
  oxygenSphere.setMaterial(redMaterial);

  Xform hydrogen1SideXform = new Xform(createH(whiteMaterial), createBond(greyMaterial));

  Xform hydrogen2SideXform = new Xform(createH(whiteMaterial), createBond(greyMaterial));
  hydrogen2SideXform.setRotateY(104.5);

  return new Xform(new Xform(oxygenSphere), hydrogen1SideXform, hydrogen2SideXform);
}

}
