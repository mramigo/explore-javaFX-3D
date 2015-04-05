package com.flowlikeariver.javafx.book;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;

public class Model {

private final ObjectProperty<Material> material = new SimpleObjectProperty<>(this, "material", new PhongMaterial(Color.BLUE));
private final ObjectProperty<DrawMode> drawMode = new SimpleObjectProperty<>(this, "drawMode", DrawMode.FILL);
private final ObjectProperty<CullFace> cullFace = new SimpleObjectProperty<>(this, "cullFace", CullFace.BACK);

public final Material getMaterial() {
  return material.get();
}

public final void setMaterial(Material material) {
  this.material.set(material);
}

public final ObjectProperty<Material> materialProperty() {
  return material;
}

public final DrawMode getDrawMode() {
  return drawMode.getValue();
}

public final void setDrawMode(DrawMode drawMode) {
  this.drawMode.set(drawMode);
}

public final ObjectProperty<DrawMode> drawModeProperty() {
  return drawMode;
}

public final CullFace getCullFace() {
  return cullFace.get();
}

public final void setCullFace(CullFace cullFace) {
  this.cullFace.set(cullFace);
}

public final ObjectProperty<CullFace> cullFaceProperty() {
  return cullFace;
}
}
