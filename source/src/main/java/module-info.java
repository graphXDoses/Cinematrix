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
    //Google Firebase and Cloud Firestore requirements.

    opens com.texnologia_logismikou.Cinematrix to javafx.fxml, google.cloud.firestore, google.cloud.storage;
    
    exports com.texnologia_logismikou.Cinematrix;
}
