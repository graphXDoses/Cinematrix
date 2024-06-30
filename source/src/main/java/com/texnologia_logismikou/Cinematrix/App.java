package com.texnologia_logismikou.Cinematrix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

import com.texnologia_logismikou.Cinematrix.RequestBodies.SignUpRequestBody;
import com.texnologia_logismikou.Cinematrix.RequestBodies.UserDocumentBody;
import com.texnologia_logismikou.Cinematrix.ResponseBodies.CreateDocumentResponseBody;
import com.texnologia_logismikou.Cinematrix.ResponseBodies.SignUpResponseBody;

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
        
        RequestController temp = new RequestController();
        CreateDocumentResponseBody response2;
        SignUpResponseBody response1;
        try {
			response1 = temp.SignUpRequest("phoebusmail@gmail.com", "anotherPassword123");
			if(response1.getError() == null) {
				response2 = temp.createUserDocumentRequest(response1.getLocalId(), response1.getIdToken());
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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