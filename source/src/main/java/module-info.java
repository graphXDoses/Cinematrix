module Cinematrix {
//	requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;

    opens com.texnologia_logismikou.Cinematrix to javafx.fxml;
    opens com.texnologia_logismikou.Cinematrix.Controllers to javafx.fxml;
    opens com.texnologia_logismikou.Cinematrix.Managers to javafx.fxml;
    
    exports com.texnologia_logismikou.Cinematrix;
    exports com.texnologia_logismikou.Cinematrix.Controllers;
    exports com.texnologia_logismikou.Cinematrix.Managers;
}
