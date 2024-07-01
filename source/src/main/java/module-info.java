module Cinematrix {
//	requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	
	requires java.net.http;
	requires com.google.gson;
	
	// Requirements for HttpRequests.
	requires java.desktop;
	requires javafx.base;
	requires javafx.web;

    opens com.texnologia_logismikou.Cinematrix to javafx.fxml, google.cloud.firestore, google.cloud.storage, com.google.gson;
    opens com.texnologia_logismikou.Cinematrix.Controllers to javafx.fxml;
    opens com.texnologia_logismikou.Cinematrix.Managers to javafx.fxml;
    opens com.texnologia_logismikou.Cinematrix.Views to javafx.fxml;
    opens com.texnologia_logismikou.Cinematrix.Users to javafx.fxml;
    opens com.texnologia_logismikou.Cinematrix.RequestBodies to com.google.gson;
    opens com.texnologia_logismikou.Cinematrix.ResponseBodies to com.google.gson;
    opens com.texnologia_logismikou.Cinematrix.DocumentObjects to com.google.gson;
    opens com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields to com.google.gson;
    
    exports com.texnologia_logismikou.Cinematrix;
    exports com.texnologia_logismikou.Cinematrix.Controllers;
    exports com.texnologia_logismikou.Cinematrix.Managers;
    exports com.texnologia_logismikou.Cinematrix.Views;
    exports com.texnologia_logismikou.Cinematrix.Users;
    exports com.texnologia_logismikou.Cinematrix.RequestBodies;
    exports com.texnologia_logismikou.Cinematrix.ResponseBodies;
    exports com.texnologia_logismikou.Cinematrix.DocumentObjects;
    exports com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;
}
