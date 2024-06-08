package com.texnologia_logismikou.Cinematrix;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

//Google Firestore & Firebase imports.
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.v1.FirestoreClient;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.core.Path;
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
        
        //Function that 
        initiateFirebase();
    }

    public static void main(String[] args) {
        launch();
    }

    private void initiateFirebase() {
    	
    	FileInputStream serviceAccount = null;
    	
    	try {
    		serviceAccount = new FileInputStream("C:/Users/petsi/University/TexLog/fir-test-java-key.json");
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
    	
    	//Create a hash map to store the items you want to add to the database. The type needs to be
    	//<String, Object>
    	HashMap<String, Object> videoGames = new HashMap<String, Object>();
    	//Change the code below and add some different values to see them added in the db
    	// ([key], [value])
    	videoGames.put("p5", "Persona 5");
    	
    	//HashMap<String, Object> newMovie = new HashMap<String, Object>();
    	Movie temp = new Movie("Rush Hour", 12, 90, 30);
    	//newMovie.put(temp.getTitle(), temp);
    	
    	//I haven't searched yet what future does.
    	ApiFuture<WriteResult> future = db.collection("TestCollection").document("GoodVideoGames").update(temp.setupMovie());
    	System.out.println("Added new entry to video games document!");
    }
}