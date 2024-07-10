package com.texnologia_logismikou.Cinematrix.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.Cinema;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.CinemaFields;
import com.texnologia_logismikou.Cinematrix.Managers.VenueEditorDetailsWidget;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditCinemaDetailsPopUpContoller {

	@FXML private Button add_venue_button;
	@FXML private TextField cinema_address_field;
	@FXML private TextField cinema_name_field;
	@FXML private TextField distance_field;
	@FXML private VBox main_vbox_container;
	@FXML private ScrollPane scroller;
	@FXML private VBox venue_container;
	
	@FXML
    void initialize()
    {    
        scroller.getStyleClass().clear();
		scroller.getStyleClass().add(".scroll-bar");
    }

	public void setData(Cinema cinema)
	{
		if(cinema != null)
		{
			CinemaFields fields = cinema.getDoc().getFields();
			cinema_name_field.setText(fields.getName().getStringValue());
			distance_field.setText(fields.getDistance().getDoubleValue() + "");
			cinema_address_field.setText(fields.getAddress().getStringValue());
		}
	}
	
    @FXML
    void addNewVenueCallback(ActionEvent event)
    {
    	venue_container.getChildren().add(new VenueEditorDetailsWidget().getParent());
    }

}
