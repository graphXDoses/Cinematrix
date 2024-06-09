package com.texnologia_logismikou.Cinematrix;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MovieModalController {

	@FXML
    private ImageView mdl_cover;
	
	public void setData(Movie movie)
	{
		Image img = new Image(getClass().getResource(movie.getUrl()).toExternalForm());
		mdl_cover.setImage(img);
	}
	
}
