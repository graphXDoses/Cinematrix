package com.texnologia_logismikou.Cinematrix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

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
        
        //https://stackoverflow.com/questions/36009764/how-to-align-ok-button-of-a-dialog-pane-in-javafx
    /*    
        Dialog<ButtonType> dialog = new Dialog<>();
        DialogPane dialogPane = new DialogPane() {
            @Override
            protected Node createButtonBar() {
                ButtonBar buttonBar = (ButtonBar) super.createButtonBar();
                buttonBar.setButtonOrder(ButtonBar.BUTTON_ORDER_NONE);

                return buttonBar;
            }
        };
        dialog.setDialogPane(dialogPane);
        dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);
        dialogPane.setContentText("Centered Button");

        Region spacer = new Region();
        ButtonBar.setButtonData(spacer, ButtonBar.ButtonData.BIG_GAP);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        dialogPane.applyCss();
        HBox hbox = (HBox) dialogPane.lookup(".container");
        hbox.getChildren().add(spacer);

        show.setOnAction(e -> {
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("OK");
            }
        });
        
        dialog.showAndWait();
        */
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