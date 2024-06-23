package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.Cinema;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CinemaModalController {

	@FXML private Label cinema_address;
	@FXML private Label cinema_name;
	@FXML private Label cinema_rel_distance;
	
    @FXML
    void initialize() {

    }
    
    public void setData(Cinema cinema)
    {
    	cinema_name.setText(cinema.getName());
    	cinema_address.setText(cinema.getAddress());
    	cinema_rel_distance.setText(cinema.getDistance() + " km");
    }

}
