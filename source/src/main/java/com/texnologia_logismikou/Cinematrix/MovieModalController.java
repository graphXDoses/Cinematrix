package com.texnologia_logismikou.Cinematrix;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class MovieModalController {

	@FXML
    private Button mdl_button;
	
	@FXML
    private ImageView mdl_cover;
	
	@FXML
    private HBox mld_halo;
	
	public void setData(Movie movie)
	{
		Image img = new Image(getClass().getResource(movie.getUrl()).toExternalForm());
		mdl_cover.setImage(img);
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
	}
	
}
