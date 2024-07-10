package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.App;
import com.texnologia_logismikou.Cinematrix.Cinema;
import com.texnologia_logismikou.Cinematrix.Controllers.EditCinemaDetailsPopUpContoller;

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

public class EditCinemaDetailsPopUp
extends Manager<EditCinemaDetailsPopUpContoller>
{
	private Dialog<ButtonType> dialog = null;
	private Cinema cinema = null;
	
	public EditCinemaDetailsPopUp(Cinema cinema)
	{	
		super.loadFXML("EditCinemaDetailsPopUp");
		this.cinema = cinema;
		getController().setData(cinema);
		
		DialogPane dialogPane = new DialogPane()
		{
            @Override
            protected Node createButtonBar() {
                ButtonBar buttonBar = (ButtonBar) super.createButtonBar();
                buttonBar.setButtonOrder(ButtonBar.BUTTON_ORDER_NONE);

                return buttonBar;
            }
        };
        dialog = new Dialog<ButtonType>();
        dialog.setDialogPane(dialogPane);
        dialogPane.getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);

        Region spacer = new Region();
        ButtonBar.setButtonData(spacer, ButtonBar.ButtonData.BIG_GAP);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        dialogPane.applyCss();
        dialogPane.getStylesheets().add(App.class.getResource("styles/styles.css").toExternalForm());
        dialogPane.getStyleClass().add("license_popup_content");
        HBox hbox = (HBox) dialogPane.lookup(".container");
        hbox.getChildren().add(spacer);
//        dialog.setHeaderText(PropertiesReader.getProperty("AppName") + " - " + PropertiesReader.getProperty("AppVersion"));
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("screens/EditMovieDetailsPopUp.fxml"));
        HBox root;
		dialogPane.setContent(getParent());
        
        Stage stage = (Stage)dialog.getDialogPane().getScene().getWindow();
        stage.setTitle("Movie Details Editor");
        stage.getIcons().add(new Image(App.class.getResource("images/CinematrixIcon.png").toExternalForm()));
	}
	
	public Dialog<ButtonType> getDialog() { return(dialog); }
}
