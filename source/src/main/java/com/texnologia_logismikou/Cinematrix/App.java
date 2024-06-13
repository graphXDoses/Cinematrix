package com.texnologia_logismikou.Cinematrix;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private Cinema_System cinemaSystem = new Cinema_System();
	
    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
     
        cinemaSystem.setupFirebase();
        //cinemaSystem.handleInputMovie();
        //cinemaSystem.updateAllMovieList();
        //cinemaSystem.handleUserRegister();
        cinemaSystem.handleUserLogin();
    }

    public static void main(String[] args) {
        launch();
    }
}