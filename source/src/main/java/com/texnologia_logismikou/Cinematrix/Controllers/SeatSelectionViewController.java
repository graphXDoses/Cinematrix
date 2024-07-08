package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Screening;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SeatSelectionViewController
{
	@FXML private Label cinema_name_label;
	@FXML private ImageView cover_image;
	@FXML private Label movie_title_label;
	@FXML private HBox topdown_area_container;
	
	private Screening screening;
	
	public void setData(Screening screening, String hour)
	{
		this.screening = screening;
		cinema_name_label.setText(screening.getCinema().getName());
		movie_title_label.setText(screening.getMovie().getFullName());
		cover_image.setImage(screening.getMovie().getModal().getCoverImage());
	}
	
	@FXML
    void backToMovieDetailsCallback(ActionEvent event)
	{
		CinematrixAPI.getInstance()
		.getActiveContext()
		.promiseRedirectTo(CinematrixAPI.MOVIE_CONTEXT.MOVIE_DETAILS_VIEW);
		CinematrixAPI.getInstance().getMainDisplay().refresh();
    }
}
