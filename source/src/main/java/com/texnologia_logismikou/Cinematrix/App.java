package com.texnologia_logismikou.Cinematrix;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.TimestampField;

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
    }

    public static void main(String[] args) {
    	launch();
    }
}