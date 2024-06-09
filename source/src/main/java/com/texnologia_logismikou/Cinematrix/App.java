package com.texnologia_logismikou.Cinematrix;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

//Google Firestore & Firebase imports.
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
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
    	
    	//Close the file and exit the method.
    	try {
    		serviceAccount = new FileInputStream("C:/Users/petsi/University/TexLog/fir-test-java-key.json");
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Database error. Cannot continue operation!");
			try {
				serviceAccount.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return;
		}

    	FirebaseOptions options = null;
    	
    	//Exit the method if there is an error.
    	try {
    		options = FirebaseOptions.builder()
        			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
        			.build();
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Database error. Cannot continue operation!");
			return;
		}
    	
    	FirebaseApp.initializeApp(options);
    	Firestore db = com.google.firebase.cloud.FirestoreClient.getFirestore();
    	
    	//Create a hash map to store the items you want to add to the database. The type needs to be
    	//<String, Object>
    	//The code goes as follows: 
    	//	1) A HashMap is created <String, Object>
    	//	2) A Movie object is created with a temporary constructor that initializes some values.
    	//  3) An entry is added to the HashMap with the Movie's title as a key, and another HashMap* as its value.
    	//
    	//	*The method setupMovie() creates a HashMap <String, Object> and fills it with key, value pairs with every
    	//	 movie field being a key and its value being the value.
    	HashMap<String, Object> newMovieMap = new HashMap<String, Object>();
    	Movie newMovie = new Movie("Rush Hour", 12, 120, 30);
    	Movie newMovie2 = new Movie("Perfect Blue", 15, 90, 28);
    	newMovieMap.put(newMovie.getTitle(), newMovie.setupMovie());
    	newMovieMap.put(newMovie2.getTitle(), newMovie2.setupMovie());
    	
    	//I haven't searched yet what future does. It has to do with asynchronous actions taking place.
    	//The update() method adds an entry to the document with name Movie, in the collection named TestCollection.
    	//newMovieMap is a HashMap as mentioned above, with only 1 pair.
    	//key: movie's name, value: a HashMap with all the movie's details (created in step 3 above).
    	ApiFuture<WriteResult> future = db.collection("TestCollection").document("Movies").update(newMovieMap);
    	System.out.println("Added new entry to Movies!");
    	System.out.println("------------------------\n\n");
    	
    	//The following code prints the contents of a document.
    	DocumentReference doc = db.collection("TestCollection").document("Movies");
    	ApiFuture<DocumentSnapshot> snapFuture = doc.get();
    	DocumentSnapshot docSnap = null;
    	
    	try {
			docSnap = snapFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(docSnap.exists()) {
    		
    		System.out.println("Document data: " + docSnap.getData());
    	} else {
    		System.out.println("No such document exists!");
    	}
    }
}