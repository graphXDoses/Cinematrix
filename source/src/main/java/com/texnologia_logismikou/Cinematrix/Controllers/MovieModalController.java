package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.FileNotFoundException;

import com.texnologia_logismikou.Cinematrix.App;
import com.texnologia_logismikou.Cinematrix.CinemaSystem;
import com.texnologia_logismikou.Cinematrix.Managers.Movie;
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
	
	public void setData(Movie movie)
	{
		try
		{			
			Image img = new Image(App.class.getResource(movie.getUrl()).toExternalForm());
			mdl_cover.setImage(img);
			mdl_button.setTooltip(new Tooltip("<Movie Title>"));
		} catch(Exception e) {
			mdl_cover.setOpacity(.3);
			edit_btn.setVisible(true);
			mdl_button.setTooltip(new Tooltip("Add new movie."));
		}
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
        
        edit_btn.setVisible(false);
	}
	
	@FXML
    void addNewMovie(ActionEvent event) {
		View details = CinemaSystem.Invoke().getActiveContext().getViews().get(1);
		try {
			CinemaSystem.Invoke().getActiveContext().goToView(details);
			CinemaSystem.Invoke().getMainDisplay().refresh();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
