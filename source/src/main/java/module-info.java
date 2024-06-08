module com.texnologia_logismikou.Cinematrix {
    requires javafx.controls;
    
    //Google Firebase and Cloud Firestore requirements.
    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires com.google.auth;
    requires google.cloud.firestore;
    requires com.google.api.apicommon;
    requires google.cloud.core;
    //Google Firebase and Cloud Firestore requirements.
    
    exports com.texnologia_logismikou.Cinematrix;
}
