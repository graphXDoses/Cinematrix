package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.FileNotFoundException;

import com.texnologia_logismikou.Cinematrix.CinemaSystem;
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
    	SeatSelectionView seatSelection = (SeatSelectionView) CinemaSystem.getInstance().getActiveContext().getViews().get(2);
		try {
			CinemaSystem.getInstance().getActiveContext().goToView(seatSelection);
			CinemaSystem.getInstance().getMainDisplay().refresh();
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
