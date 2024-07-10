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

    	CinematrixAPI.getInstance().makeCinematrixDir();
    	
    	CinematrixAPI.getInstance().fetchCinemasFromDatabase();
    	CinematrixAPI.getInstance().fetchMoviesFromDatabase();
//    	CinematrixAPI.getInstance().fetchScreeningsFromDatabase();

    	try {
    		CinematrixAPI.getInstance().userSignIn("austinnaley@vasilis.karas.gr", "hs$98xuu");
    	} catch (SignInException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        
    	CinematrixAPI.getInstance().placeUIOnStage(stage);
    	CinematrixAPI.getInstance().getMainDisplay().refresh();
    }

    public static void main(String[] args) {
    	launch();
    }
}