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

	@FXML private Button mdl_button;
	@FXML private ImageView mdl_cover;
	@FXML private HBox mld_halo;
	@FXML private HBox edit_btn;
	
	private Movie associateMovie = null;
	
	public void setData(Movie movie)
	{
		associateMovie = movie;
		
		mdl_cover.setImage(movie.getModal().getCoverImage());
		mdl_button.setTooltip(new Tooltip(movie.getDoc().getFields().getTitle().getStringValue()));
		edit_btn.setVisible(false);
		mdl_cover.setOpacity(1);
	}
	
	@FXML
	void initialize()
	{
		final FadeTransition fadeIn = new FadeTransition(Duration.millis(300));
        fadeIn.setNode(mld_halo);
        fadeIn.setToValue(1);
        mdl_button.setOnMouseEntered(e -> fadeIn.playFromStart());

        final FadeTransition fadeOut = new FadeTransition(Duration.millis(300));
        fadeOut.setNode(mld_halo);
        fadeOut.setToValue(0.0);
        mdl_button.setOnMouseExited(e -> fadeOut.playFromStart());

        mld_halo.setOpacity(0.0);

        mdl_button.setTooltip(new Tooltip("Add new movie."));
        mdl_cover.setOpacity(.3);
	}
	
	@FXML
    void gotoDetailsCallback(ActionEvent event) {
		MovieDetailsView details = (MovieDetailsView) CinematrixAPI.getInstance().getActiveContext().getViews().get(1);
		try {
			CinematrixAPI.getInstance().getActiveContext().goToView(details);
			details.setSelectedMovie(associateMovie);
			CinematrixAPI.getInstance().getMainDisplay().refresh();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
