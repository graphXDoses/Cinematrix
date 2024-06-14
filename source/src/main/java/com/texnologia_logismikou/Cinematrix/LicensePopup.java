package com.texnologia_logismikou.Cinematrix;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class LicensePopup extends Dialog<ButtonType> {

	private static LicensePopup instance = null;
	private static PropertiesReader reader = null;
	
	private LicensePopup()
	{
		try {
			reader = new PropertiesReader("properties-from-pom.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DialogPane dialogPane = new DialogPane() {
            @Override
            protected Node createButtonBar() {
                ButtonBar buttonBar = (ButtonBar) super.createButtonBar();
                buttonBar.setButtonOrder(ButtonBar.BUTTON_ORDER_NONE);

                return buttonBar;
            }
        };
        setDialogPane(dialogPane);
        dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);
        //dialogPane.setContentText(MITLicense());

        Region spacer = new Region();
        ButtonBar.setButtonData(spacer, ButtonBar.ButtonData.BIG_GAP);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        dialogPane.applyCss();
        dialogPane.getStylesheets().add(getClass().getResource("styles/styles.css").toExternalForm());
        dialogPane.getStyleClass().add("license_popup_content");
        HBox hbox = (HBox) dialogPane.lookup(".container");
        hbox.getChildren().add(spacer);
        setHeaderText(reader.getProperty("AppName") + " - " + reader.getProperty("AppVersion"));
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("screens/AboutUsPopUp.fxml"));
        HBox root;
		try {
			root = loader.load();
			LicenseScrollPaneController controller = loader.getController();
			dialogPane.setContent(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Stage stage = (Stage)getDialogPane().getScene().getWindow();
        stage.setTitle("Software License");
        stage.getIcons().add(new Image(getClass().getResource("images/CinematrixIcon.png").toExternalForm()));
	}
	
	public static LicensePopup getInstance()
	{
		if(instance == null)
		{
			instance = new LicensePopup();
		}
		return(instance);
	}
}
