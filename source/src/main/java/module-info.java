module Cinematrix {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.texnologia_logismikou.Cinematrix to javafx.fxml;
    exports com.texnologia_logismikou.Cinematrix;
//	requires javafx.graphics;
}
