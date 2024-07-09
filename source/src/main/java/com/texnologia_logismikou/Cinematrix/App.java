package com.texnologia_logismikou.Cinematrix;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application
{

    @Override
    public void start(Stage stage) throws IOException {
    	CinematrixAPI.getInstance().fetchCinemasFromDatabase();
    	CinematrixAPI.getInstance().fetchMoviesFromDatabase();

    	CinematrixAPI.getInstance().placeUIOnStage(stage);
        CinematrixAPI.getInstance().getMainDisplay().refresh();
    }

    public static void main(String[] args) {
    	launch();
    }
}