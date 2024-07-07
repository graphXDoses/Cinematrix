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
    	SeatSelectionView seatSelection = (SeatSelectionView) CinematrixAPI.getInstance().getActiveContext().getViews().get(2);
		try {
			CinematrixAPI.getInstance().getActiveContext().goToView(seatSelection);
			CinematrixAPI.getInstance().getMainDisplay().refresh();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setHour(String hour)
    {
    	root.setText(hour);
    }

}
