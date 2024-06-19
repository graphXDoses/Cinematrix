module Cinematrix {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.texnologia_logismikou.Cinematrix to javafx.fxml;
    exports com.texnologia_logismikou.Cinematrix;
    
    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.materialdesignicons;
    requires de.jensd.fx.glyphs.fontawesome;
}
