package com.texnologia_logismikou.Cinematrix;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.ScreeningFields;

/**
 * JavaFX App
 */
public class App extends Application
{

    @Override
    public void start(Stage stage) throws IOException {
    	
    	CinematrixAPI.getInstance().makeCinematrixDir();
    	
    	CinematrixAPI.getInstance().fetchCinemasFromDatabase();
    	CinematrixAPI.getInstance().fetchMoviesFromDatabase();
    	CinematrixAPI.getInstance().fetchScreeningsFromDatabase();

    	CinematrixAPI.getInstance().placeUIOnStage(stage);
        CinematrixAPI.getInstance().getMainDisplay().refresh();
        
        /*LocalDateTime time = LocalDateTime.of(2024, 7, 10, 19, 30);
        ScreeningFields fields = new ScreeningFields("pearl_1720525250172", "village_1720520811335", "venueb_1720530586387", time);
        CinematrixAPI.getInstance().createScreening(fields, "");*/
    }

    public static void main(String[] args) {
    	launch();
    }
}