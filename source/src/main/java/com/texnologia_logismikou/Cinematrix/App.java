package com.texnologia_logismikou.Cinematrix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.MovieFields;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.StringField;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Main"));
        scene.getStylesheets().add(getClass().getResource("styles/styles.css").toExternalForm());
        
        stage.setTitle("Cinematrix");
        stage.getIcons().add(new Image(getClass().getResource("images/CinematrixIcon.png").toExternalForm()));
        stage.setScene(scene);
        stage.show();
        
        CinemaSystem.getInstance().fetchCinemasFromDatabase();
        CinemaSystem.getInstance().fetchMoviesFromDatabase();
        CinemaSystem.getInstance().getMainDisplay().refresh();
        
        String idToken;
		idToken = "";
	    
		String[] categories = {"Drama", "Horror", "Thriller"};
		String[] cinemas = {"Vakoura", "Cineplex"};
	    MovieFields fields = new MovieFields("Pearl", 2022, "R", 103, "https://www.imdb.com/video/vi3608199705/?playlistId=tt18925334&ref_=tt_ov_vi", categories, cinemas, "In 1918, a young woman on the brink of madness pursues stardom in a desperate attempt to escape the drudgery, isolation, and lovelessness of life on her parents' farm.", "Ti West");     
	    CinemaSystem.getInstance().updateMovieDocument(fields, idToken); // <--- The id token is missing now so this doesn't work. Normally an admin has to sign in and use their FirebaseID.
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("screens/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
    	launch();
    }

}