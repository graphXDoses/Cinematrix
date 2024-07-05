package com.texnologia_logismikou.Cinematrix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.ArrayField;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.MapField;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.MovieFields;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.RoomFields;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.ScreeningFields;
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
        
        String id = "";
        
        try {
			id = CinemaSystem.getInstance().userSignIn("phoebuspetsi@gmail.com", "myPassword123");
			
			ScreeningFields screenFields = new ScreeningFields("Interstellar", 2100, 000);
			ScreeningFields screenFields2 = new ScreeningFields("Rush-Hour", 1600, 000);
			ScreeningFields screenFields3 = new ScreeningFields("Spiritied-Away", 2400, 000);
			
			ScreeningFields[] array = {screenFields, screenFields2, screenFields3};
			
			RoomFields fields = new RoomFields("room1", array);
			try {
				RequestHandler.getInstance("AIzaSyDTn8MSxkAuIX-sH-_I_vwAwVqIt77sORU").updateRoomDocumentRequest(id, fields);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
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