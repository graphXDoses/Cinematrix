package com.texnologia_logismikou.Cinematrix;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.v1.FirestoreClient;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void initiateFirebase() {
    	
    	FileInputStream serviceAccount = null;
    	
    	try {
    		serviceAccount = new FileInputStream("C:/Users/petsi/University/TexLog/serviceAccountKey.json");
		} catch (Exception e) {
			System.out.println(e.toString());
		}

    	FirebaseOptions options = null;
    	
    	try {
    		options = FirebaseOptions.builder()
        			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
        			.build();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
    			  
    	FirebaseApp.initializeApp(options);
    	Firestore db = com.google.firebase.cloud.FirestoreClient.getFirestore();
    }
}