package com.flowlikeariver.javafx.book.mesh;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class View {

public Scene scene;

public MeshView meshView;

public ColorPicker colorPicker;
public ComboBox<DrawMode> drawModeComboBox;
public ComboBox<CullFace> cullFaceComboBox;
public Slider rotateSlider;

public View(Model model) {
  meshView = new MeshView(createPrism(200f, 200f));
  meshView.materialProperty().bind(model.materialProperty());
  meshView.drawModeProperty().bind(model.drawModeProperty());
  meshView.cullFaceProperty().bind(model.cullFaceProperty());
  meshView.setRotationAxis(new Point3D(1, 1, 1));
  meshView.rotateProperty().bind(model.rotateProperty());

  colorPicker = new ColorPicker(Color.BLUE);

  drawModeComboBox = new ComboBox<>();
  drawModeComboBox.setItems(FXCollections.observableArrayList(DrawMode.FILL, DrawMode.LINE));
  drawModeComboBox.setValue(DrawMode.FILL);

  cullFaceComboBox = new ComboBox<>();
  cullFaceComboBox.setItems(FXCollections.observableArrayList(CullFace.BACK, CullFace.FRONT, CullFace.NONE));
  cullFaceComboBox.setValue(CullFace.BACK);

  HBox hbox1 = new HBox(10, new Label("Color:"), colorPicker,
    new Label("DrawMode:"), drawModeComboBox,
    new Label("CullFace:"), cullFaceComboBox);
  hbox1.setPadding(new Insets(10, 10, 10, 10));
  hbox1.setAlignment(Pos.CENTER);

  rotateSlider = new Slider(-180.0d, 180.0d, 60.0d);
  rotateSlider.setMinWidth(400.0d);
  rotateSlider.setMajorTickUnit(20.0d);
//  rotateSlider.setMinorTickCount(5);
  rotateSlider.setShowTickMarks(true);
  rotateSlider.setShowTickLabels(true);

  rotateSlider.valueProperty().bindBidirectional(model.rotateProperty());

  HBox hbox2 = new HBox(10, new Label("Rotate Around (1, 1, 1) Axis:"), rotateSlider);
  hbox2.setPadding(new Insets(10, 10, 10, 10));
  hbox2.setAlignment(Pos.CENTER_LEFT);

  VBox controlPanel = new VBox(10, hbox1, hbox2);
  controlPanel.setPadding(new Insets(10, 10, 10, 10));

  BorderPane root = new BorderPane(meshView, null, null, controlPanel, null);

  scene = new Scene(root, 640, 480);
}

private Mesh createPyramid(float length) {
  TriangleMesh mesh = new TriangleMesh();
  float unit = length / 2;
  float third = (float) (unit / Math.sqrt(3));
  mesh.getPoints().addAll(
    -unit, 0, third, // O
    unit, 0, third, // A
    0, 0, -2 * third, // B
    0, - (float) (third * 2 * Math.sqrt(2)), 0 // C
  );

  mesh.getTexCoords().addAll(
    0.0f, 0.0f,
    0.0f, 1.0f,
    1.0f, 0.0f,
    1.0f, 1.0f
  );

  mesh.getFaces().addAll(
    0, 0, 2, 1, 1, 2, // OBA
    0, 0, 3, 1, 2, 2, // OCB
    0, 0, 1, 1, 3, 2, // OAC
    1, 0, 2, 1, 3, 2 // ABC
  );

  mesh.getFaceSmoothingGroups().addAll(0, 0, 0, 0);
  return mesh;
}

private Mesh createPrism(float length, float height) {
  TriangleMesh mesh = new TriangleMesh();
  float unit = length / 2;
  float third = (float) (unit / Math.sqrt(3));
  mesh.getPoints().addAll(
    -unit, 0, third, // O
    unit, 0, third, // A
    0, 0, -2 * third, // B
    -unit, -height, third, // C
    unit, -height, third, // D
    0, -height, -2 * third // E
  );

  mesh.getTexCoords().addAll(
    0.0f, 0.0f,
    0.0f, 1.0f,
    1.0f, 0.0f,
    1.0f, 1.0f
  );

  mesh.getFaces().addAll(
    0, 0, 1, 1, 2, 2, // OAB
    0, 0, 3, 1, 1, 2, // OCA
    1, 0, 3, 1, 4, 2, // ACD
    0, 0, 2, 1, 5, 2, // OBE  
    0, 0, 5, 1, 3, 2, // OEC
    2, 0, 1, 1, 4, 2, // BAD
    2, 0, 4, 1, 5, 2, // BDE
    3, 0, 5, 1, 4, 2 // CED
  );

  mesh.getFaceSmoothingGroups().addAll(0, 0, 0, 0, 0, 0, 0, 0);
  return mesh;
}

private Mesh createPyramidOriginal(float length) {
  TriangleMesh mesh = new TriangleMesh();

  mesh.getPoints().addAll(
    0.0f, 0.0f, 0.0f, // O
    length, 0.0f, 0.0f, // A
    length / 2, (float) (Math.sqrt(3) * length / 2), 0.0f, // B
    length / 2, (float) (length / 2 * Math.sqrt(3) / 3), (float) (length * Math.sqrt(2) / Math.sqrt(3)) // C
  );

  mesh.getTexCoords().addAll(
    0.0f, 0.0f,
    0.0f, 1.0f,
    1.0f, 0.0f,
    1.0f, 1.0f
  );

  mesh.getFaces().addAll(
    0, 0, 2, 1, 1, 2, // OBA
    0, 0, 3, 1, 2, 2, // OCB
    0, 0, 1, 1, 3, 2, // OAC
    1, 0, 2, 1, 3, 2 // ABC
  );

  mesh.getFaceSmoothingGroups().addAll(0, 0, 0, 0);

  return mesh;
}
}
