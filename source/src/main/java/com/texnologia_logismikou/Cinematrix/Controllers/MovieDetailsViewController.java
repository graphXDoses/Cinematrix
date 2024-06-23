package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.Movie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MovieDetailsViewController {

	@FXML private ImageView mdl_cover;
	@FXML private Label mdl_title;
	@FXML private SplitPane root;
	
	private static Image     blankCover;
	private static String    defaultText;

    @FXML
    void initialize() {
        assert mdl_cover != null : "fx:id=\"mdl_cover\" was not injected: check your FXML file 'MovieDetailsView.fxml'.";
        assert mdl_title != null : "fx:id=\"mdl_title\" was not injected: check your FXML file 'MovieDetailsView.fxml'.";
        assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'MovieDetailsView.fxml'.";

        root.getStyleClass().clear();
		root.getStyleClass().add("crank");
		
		blankCover  = mdl_cover.getImage();
		defaultText =  mdl_title.getText();
    }
    
    public void setData(Movie movie)
	{
    	if(movie != null)
    	{    		
    		mdl_cover.setImage(movie.getModal().getCoverImage());
    		mdl_title.setText(movie.getFullName());
    	} else {
    		mdl_cover.setImage(blankCover);
    		mdl_title.setText(defaultText);
    	}
	}

}
