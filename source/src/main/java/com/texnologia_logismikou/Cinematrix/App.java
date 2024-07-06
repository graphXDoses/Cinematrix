package com.texnologia_logismikou.Cinematrix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.MovieDocument;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.ArrayField;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.MapField;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.MovieFields;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.RoomFields;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.ScreeningFields;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.StringField;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.TimestampField;

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
        
        String id;
		try {
			id = CinemaSystem.getInstance().userSignIn("phoebuspetsi@gmail.com", "myPassword123");
			MovieDocument response = new MovieDocument();
			//CinemaSystem.getInstance().createMovieDocument("Spirited Away", id);
			
			String[] categories = {"Animation", "Adventure", "Family", "Fantasy", "Mystery"};
			String[] cinemas = {"Village", "Cineplex"};
			MovieFields fields = new MovieFields("Spirited Away", 2001, "PG", 125, "https://youtu.be/ByXuk9QqQkk?si=r6es_Sx50h5RZe6F", categories, cinemas, "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches and spirits, and where humans are changed into beasts.", "Hayao Miyazaki", "SpritedAway");
			
			//CinemaSystem.getInstance().updateMovieDocument(fields, id);
			response = CinemaSystem.getInstance().getMovieDocument("Spirited Away");
			StorageHandler temp = new StorageHandler();
			try {
				temp.downloadMovieImage(response.getFields().getTitle().getStringValue());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SignInException e) {
			// TODO Auto-generated catch block
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