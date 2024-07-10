package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Screening;
import com.texnologia_logismikou.Cinematrix.Views.SeatSelectionView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ReservationButtonController {

    @FXML
    private Button root;
    
    private Screening     screening;
    private LocalDateTime selectedHour;

    @FXML
    void makeReservationCallback(ActionEvent event)
    {
    	CinematrixAPI.MOVIE_CONTEXT.SEAT_SELECTION_VIEW = new SeatSelectionView(screening, selectedHour);
    	CinematrixAPI.getInstance()
    				.getActiveContext()
    				.promiseRedirectTo(CinematrixAPI.MOVIE_CONTEXT.SEAT_SELECTION_VIEW);
    	CinematrixAPI.getInstance().getMainDisplay().refresh();
    }
    
    public void setData(Screening screening, LocalDateTime hour)
    {
    	this.screening = screening;
    	this.selectedHour = hour;

    	String hourString = hour.format(DateTimeFormatter.ofPattern("h:mma")).toLowerCase(); 
    	root.setText(hourString.substring(0, hourString.indexOf('m')));
    }

}
