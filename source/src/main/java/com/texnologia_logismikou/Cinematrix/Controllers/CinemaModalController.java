package com.texnologia_logismikou.Cinematrix.Controllers;

import java.util.Date;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.Cinema;
import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Screening;
import com.texnologia_logismikou.Cinematrix.Managers.ReservationButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CinemaModalController {

	@FXML private Label cinema_address_label;
	@FXML private Label cinema_name_label;
	@FXML private Label cinema_rel_distance_label;
	@FXML private Label sound_system_label;
	@FXML private HBox  hours_container;
	
	private Cinema associateCinema = null;
	private Movie  associateMovie  = null;
	
	@FXML
	void initialize()
	{
//		System.out.println("INIT");
		hours_container.setVisible(false);
	}
	
    public void setData(Cinema cinema)
    {
//    	System.out.println("SET DATA");
    	associateCinema = cinema;
    	
    	cinema_name_label.setText(cinema.getDoc().getFields().getName().getStringValue());
    	cinema_address_label.setText(cinema.getDoc().getFields().getAddress().getStringValue());
    	cinema_rel_distance_label.setText(cinema.getDoc().getFields().getDistance().getDoubleValue() + " km");
    	hours_container.setVisible(false);
    }

    public void setScreeningData(Screening screening)
    {
//    	System.out.println("SCREEN");
    	associateMovie = screening.getMovie();
    	
//    	sound_system_label.setText(screening.getVenue().getSystem().toString());
    	
    	for(String h: screening.getHours())
    		addReservationButton(new ReservationButton(screening, h));
    }

	public void addReservationButton(ReservationButton reservationButton)
	{
//		System.out.println("RESERVE");
		hours_container.setVisible(true);
		hours_container.getChildren().add(reservationButton.getParent());
	}
}
