package com.texnologia_logismikou.Cinematrix.Controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Screening;
import com.texnologia_logismikou.Cinematrix.Views.TicketRevisionView;

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
	@FXML private Label datetime_label;
	@FXML private HBox topdown_area_container;
	
	private Screening screening;
	
	public void setData(Screening screening, LocalDateTime hour)
	{
		this.screening = screening;
		cinema_name_label.setText(screening.getCinema().getDoc().getFields().getName().getStringValue());
		movie_title_label.setText(screening.getMovie().getDoc().getFields().getTitle().getStringValue());
		cover_image.setImage(screening.getMovie().getModal().getCoverImage());
		datetime_label.setText(
			hour.format(DateTimeFormatter.ofPattern("EEEE, MMM d 'at' h:mm a", Locale.ENGLISH))
		);
	}
	
	@FXML
    void backToMovieDetailsCallback(ActionEvent event)
	{
		CinematrixAPI.getInstance()
		.getActiveContext()
		.promiseRedirectTo(CinematrixAPI.MOVIE_CONTEXT.MOVIE_DETAILS_VIEW);
		CinematrixAPI.getInstance().getMainDisplay().refresh();
    }
	
	@FXML
    void confirmSeatSelectionCallback(ActionEvent event)
	{
		CinematrixAPI.getInstance()
		.getActiveContext()
		.promiseRedirectTo(CinematrixAPI.MOVIE_CONTEXT.TICKET_REVISION_VIEW = new TicketRevisionView());
		CinematrixAPI.getInstance().getMainDisplay().refresh();
    }
}
