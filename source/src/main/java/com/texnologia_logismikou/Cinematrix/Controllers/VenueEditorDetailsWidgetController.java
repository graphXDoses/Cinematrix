package com.texnologia_logismikou.Cinematrix.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VenueEditorDetailsWidgetController {

	@FXML
    private VBox venue_info_container;

    @FXML
    private ChoiceBox<String> venue_next_trait_choice;

    @FXML
    private ChoiceBox<String> venue_sound_system_choice;

    @FXML
    private ChoiceBox<String> venue_topdown_choice;

    @FXML
    private HBox venue_trait_labels_container;

    @FXML
    private HBox venue_traits_container;
    
    @FXML
    void initialize()
    {
    	
    }

}
