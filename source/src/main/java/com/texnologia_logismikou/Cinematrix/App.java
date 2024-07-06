package com.texnologia_logismikou.Cinematrix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

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
        
        try {
			String id = CinemaSystem.getInstance().userSignIn("phoebuspetsi@gmail.com", "myPassword123");
	        CinemaSystem.getInstance().createCinemaDocument("Village", id, 5);
		} catch (SignInException e) {
			e.printStackTrace();
		}
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