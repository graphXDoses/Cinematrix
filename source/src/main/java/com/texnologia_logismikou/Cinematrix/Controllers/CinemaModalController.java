package com.texnologia_logismikou.Cinematrix.Controllers;

import java.util.Date;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.Cinema;
import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Managers.ReservationButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CinemaModalController {

	@FXML private Label cinema_address;
	@FXML private Label cinema_name;
	@FXML private Label cinema_rel_distance;
	@FXML private HBox  hoursContainer;
	
	private Cinema associateCinema = null;
	private Movie  associateMovie  = null;
	
	@FXML
	void initialize()
	{
		System.out.println("INIT");
		hoursContainer.setVisible(false);
	}
	
    public void setData(Cinema cinema)
    {
    	System.out.println("SET DATA");
    	associateCinema = cinema;
    	
    	cinema_name.setText(cinema.getName());
    	cinema_address.setText(cinema.getAddress());
    	cinema_rel_distance.setText(cinema.getDistance() + " km");
    	hoursContainer.setVisible(false);
    }

    public void setScreeningData(Movie movie, String soundSystem, List<Date> dates, List<String> hours)
    {
    	System.out.println("SCREEN");
    	associateMovie = movie;
    	
    	for(String h: hours)
    		addReservationButton(new ReservationButton(h));
    }

	public void addReservationButton(ReservationButton reservationButton)
	{
		System.out.println("RESERVE");
		hoursContainer.setVisible(true);
		hoursContainer.getChildren().add(reservationButton.getParent());
	}
}
