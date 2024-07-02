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
		CinemaSystem.getInstance().createMovieDocument("Knives Out", idToken);
	        
	    StringField[] cinemas = {new StringField("Cineplex"), new StringField("Village"), new StringField("Athineon")};
	    StringField[] categories = {new StringField("Comedy"), new StringField("Crime"), new StringField("Drama"), new StringField("Thriller"), new StringField("Mystery")};
	    MovieFields fields = new MovieFields("Knives Out", 2019, "PG-13", 130, "hhttps://www.imdb.com/video/vi2464857881/?playlistId=tt8946378&ref_=tt_ov_vi", categories, cinemas, "A detective investigates the death of the patriarch of an eccentric, combative family.", "Rian Johnson");
	        
	    CinemaSystem.getInstance().updateMovieDocument(fields, idToken);
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