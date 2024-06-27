package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.Managers.CinemaModal;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class NearCinemasViewController {

    @FXML private VBox cinema_modals_area;

    @FXML
    void initialize() {
        assert cinema_modals_area != null : "fx:id=\"cinema_modals_area\" was not injected: check your FXML file 'NearCinemasView.fxml'.";
//        cinema_modals_area.setVisible(false);
    }

	public void appendCinemaModal(CinemaModal modal)
	{
		Parent parentNode = modal.getParent();
//		cinema_modals_area.setVisible(true);
		if(!cinema_modals_area.getChildren().contains(parentNode))
			cinema_modals_area.getChildren().add(modal.getParent());
	}

}
