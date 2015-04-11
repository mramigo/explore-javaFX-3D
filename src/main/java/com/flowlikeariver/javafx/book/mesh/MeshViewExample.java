package com.flowlikeariver.javafx.book.mesh;

import javafx.application.Application;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;

public class MeshViewExample extends Application {

private final Model model;
private View view;

public MeshViewExample() {
  model = new Model();
}

@Override
public void start(Stage stage) throws Exception {
  view = new View(model);
  hookupEvents();
  stage.setTitle("MeshView Example");
  stage.setScene(view.scene);
  stage.show();
}

private void hookupEvents() {
  view.colorPicker.setOnAction(event -> model.setMaterial(new PhongMaterial(((ColorPicker) event.getSource()).getValue())));

  view.drawModeComboBox.setOnAction(event -> {
    ComboBox<DrawMode> drawModeComboBox = (ComboBox<DrawMode>) event.getSource();
    model.setDrawMode(drawModeComboBox.getValue());
  });

  view.cullFaceComboBox.setOnAction(event -> {
    ComboBox<CullFace> cullFaceComboBox = (ComboBox<CullFace>) event.getSource();
    model.setCullFace(cullFaceComboBox.getValue());
  });
}

public static void main(String[] args) {
  launch(args);
}

}
