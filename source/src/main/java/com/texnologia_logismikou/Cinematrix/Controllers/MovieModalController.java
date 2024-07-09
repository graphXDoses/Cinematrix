package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.FileNotFoundException;

import com.texnologia_logismikou.Cinematrix.App;
import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;
import com.texnologia_logismikou.Cinematrix.Views.MovieDetailsView;
import com.texnologia_logismikou.Cinematrix.Views.View;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class MovieModalController {

	@FXML private Button modal_button;
	@FXML private ImageView modal_cover;
	@FXML private HBox modal_halo;
	@FXML private HBox edit_button;
	
	private Movie associateMovie = null;
	
	public void setData(Movie movie)
	{
		associateMovie = movie;
		
		modal_cover.setImage(movie.getModal().getCoverImage());
		//modal_button.setTooltip(new Tooltip(movie.getFullName())); // TODO Add full name to movie fields.
		edit_button.setVisible(false);
		modal_cover.setOpacity(1);
	}
	
	@FXML
	void initialize()
	{
		final FadeTransition fadeIn = new FadeTransition(Duration.millis(300));
        fadeIn.setNode(modal_halo);
        fadeIn.setToValue(1);
        modal_button.setOnMouseEntered(e -> fadeIn.playFromStart());

        final FadeTransition fadeOut = new FadeTransition(Duration.millis(300));
        fadeOut.setNode(modal_halo);
        fadeOut.setToValue(0.0);
        modal_button.setOnMouseExited(e -> fadeOut.playFromStart());

        modal_halo.setOpacity(0.0);

        modal_button.setTooltip(new Tooltip("Add new movie."));
        modal_cover.setOpacity(.3);
	}
	
	@FXML
    void gotoDetailsCallback(ActionEvent event)
	{
		CinematrixAPI.MOVIE_CONTEXT.MOVIE_DETAILS_VIEW = new MovieDetailsView();
		CinematrixAPI.MOVIE_CONTEXT.MOVIE_DETAILS_VIEW.setSelectedMovie(associateMovie);
		CinematrixAPI.getInstance()
					.getActiveContext()
					.promiseRedirectTo(CinematrixAPI.MOVIE_CONTEXT.MOVIE_DETAILS_VIEW);
		CinematrixAPI.getInstance().getMainDisplay().refresh();
    }
	
}
