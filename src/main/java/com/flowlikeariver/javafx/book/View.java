package com.flowlikeariver.javafx.book;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;

public class View {

public static final Node EMPTY_BORDER = null;

public Scene scene;
public Sphere sphere;
public ColorPicker colorPicker;
public ComboBox<DrawMode> drawModeComboBox;
public ComboBox<CullFace> cullFaceComboBox;

public View(Model model) {
  sphere = new Sphere(100);
  sphere.materialProperty().bind(model.materialProperty());
  sphere.drawModeProperty().bind(model.drawModeProperty());
  sphere.cullFaceProperty().bind(model.cullFaceProperty());

  colorPicker = new ColorPicker(Color.BLUE);

  drawModeComboBox = new ComboBox<>();
  drawModeComboBox.setItems(FXCollections.observableArrayList(DrawMode.FILL, DrawMode.LINE));
  drawModeComboBox.setValue(DrawMode.FILL);

  cullFaceComboBox = new ComboBox<>();
  cullFaceComboBox.setItems(FXCollections.observableArrayList(CullFace.BACK, CullFace.FRONT, CullFace.NONE));
  cullFaceComboBox.setValue(CullFace.BACK);

  HBox hbox = new HBox(10,
    new Label("Color:"), colorPicker,
    new Label("DrawMode:"), drawModeComboBox,
    new Label("CullFace:"), cullFaceComboBox);
  hbox.setPadding(new Insets(10, 10, 10, 10));
  hbox.setAlignment(Pos.CENTER);
  BorderPane root = new BorderPane(sphere, EMPTY_BORDER, EMPTY_BORDER, hbox, EMPTY_BORDER);
  scene = new Scene(root, 640, 480);
}
}
