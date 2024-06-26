module Cinematrix {
//	requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	
	//Google Firebase and Cloud Firestore requirements.
    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires com.google.auth;
    requires google.cloud.firestore;
    requires com.google.api.apicommon;
    requires google.cloud.core;
    requires google.cloud.storage;
    requires com.google.api.services.storage;
	requires gax;
    //Google Firebase and Cloud Firestore requirements.

	requires java.desktop;
	requires javafx.base;
	requires javafx.web;

    opens com.texnologia_logismikou.Cinematrix to javafx.fxml, google.cloud.firestore, google.cloud.storage;
    opens com.texnologia_logismikou.Cinematrix.Controllers to javafx.fxml;
    opens com.texnologia_logismikou.Cinematrix.Managers to javafx.fxml;
    opens com.texnologia_logismikou.Cinematrix.Views to javafx.fxml;
    opens com.texnologia_logismikou.Cinematrix.Users to javafx.fxml;
    
    exports com.texnologia_logismikou.Cinematrix;
    exports com.texnologia_logismikou.Cinematrix.Controllers;
    exports com.texnologia_logismikou.Cinematrix.Managers;
    exports com.texnologia_logismikou.Cinematrix.Views;
    exports com.texnologia_logismikou.Cinematrix.Users;
}
