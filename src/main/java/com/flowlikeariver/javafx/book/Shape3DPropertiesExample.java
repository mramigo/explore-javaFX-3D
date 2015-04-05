/* Original code from Pro JavxFX 8 published by Apress */
package com.flowlikeariver.javafx.book;

import javafx.application.Application;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;

public class Shape3DPropertiesExample extends Application {

private final Model model;
private View view;

public Shape3DPropertiesExample() {
  model = new Model();
}

@Override
public void start(Stage stage) throws Exception {
  view = new View(model);
  hookupEvents();
  stage.setTitle("Shape3D Properties Example");
  stage.setScene(view.scene);
  stage.show();
}

private void hookupEvents() {
  view.colorPicker.setOnAction(event -> {
    ColorPicker colorPicker = (ColorPicker) event.getSource();
    model.setMaterial(new PhongMaterial(colorPicker.getValue()));
  });

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
