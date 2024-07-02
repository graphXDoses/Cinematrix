package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.FileNotFoundException;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Views.SeatSelectionView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ReservationButtonController {

    @FXML
    private Button root;

    @FXML
    void makeReservationCallback(ActionEvent event)
    {
    	CinematrixAPI.getInstance()
    				.getActiveContext()
    				.promiseRedirectTo(CinematrixAPI.MOVIE_CONTEXT.SEAT_SELECTION_VIEW);
    	CinematrixAPI.getInstance().getMainDisplay().refresh();
    }
    
    public void setHour(String hour)
    {
    	root.setText(hour);
    }

}
