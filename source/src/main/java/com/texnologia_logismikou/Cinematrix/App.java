package com.texnologia_logismikou.Cinematrix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.*;

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
        
        //CinemaSystem.getInstance().fetchCinemasFromDatabase();
        //CinemaSystem.getInstance().fetchMoviesFromDatabase();
        //CinemaSystem.getInstance().getMainDisplay().refresh();
        //CinemaSystem.getInstance().userSignUp("Hawkwood", "hawkwoodthedeserter@gmail.com", "darksouls123");
        
        NameField name = new NameField("Bullet-Train");
        DoubleField duration = new DoubleField(127);
        CinemaArrayField cinemasArray = new CinemaArrayField();
        ArrayField<NameField> array = new ArrayField<NameField>();
        NameField[] arrayValues = new NameField[3];
        
        NameField cinema1 = new NameField("Village");
        NameField cinema2 = new NameField("Cineplex");
        NameField cinema3 = new NameField("Vakoura");
        
        arrayValues[0] = cinema1;
        arrayValues[1] = cinema2;
        arrayValues[2] = cinema3;
        
        array.setValues(arrayValues);
        
        cinemasArray.setArrayValue(array);
        
        MovieFields fields = new MovieFields(name, cinemasArray, duration);
        
        try {
			RequestHandler.getInstance("AIzaSyDTn8MSxkAuIX-sH-_I_vwAwVqIt77sORU").updateMovieDocumentRequest(fields);
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