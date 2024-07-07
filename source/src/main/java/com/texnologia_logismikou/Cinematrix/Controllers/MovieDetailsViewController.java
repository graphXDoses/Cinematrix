package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.MovieFields;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class MovieDetailsViewController {

	@FXML private Label MPArating_label;
	@FXML private VBox cinemaDisplayContainer;
	@FXML private Label description_label;
	@FXML private Label director_label;
	@FXML private Label duration_label;
	@FXML private ImageView mdl_cover;
	@FXML private Label mdl_title;
	@FXML private Hyperlink moreDetailsLink;
	@FXML private VBox more_details_container;
	@FXML private VBox vbox_accordion;
	@FXML private WebView ytTrailerPlayerArea;
	
	private static Image     blankCover;
	private static String    defaultText;
	
	private final String[]  detailsText    = {"MORE DETAILS", "LESS DETAILS"};
	private final int[]     modalMaxSize   = {430, 900};
	private int             expantionState = 0;

    @FXML
    void initialize() {
        assert mdl_cover != null : "fx:id=\"mdl_cover\" was not injected: check your FXML file 'MovieDetailsView.fxml'.";
        assert mdl_title != null : "fx:id=\"mdl_title\" was not injected: check your FXML file 'MovieDetailsView.fxml'.";
		
		blankCover  = mdl_cover.getImage();
		defaultText =  mdl_title.getText();
		
		vbox_accordion.setMaxHeight(modalMaxSize[0]);
		
		more_details_container.setVisible(false);
		more_details_container.getChildren().forEach(node->node.setVisible(true));
    }
    
	public void setMovieDetailData(Movie movie)
	{
    	MovieFields fields = movie.getDoc().getFields();
    	
    	if(movie != null)
    	{    		
    		mdl_cover.setImage(movie.getModal().getCoverImage());
    		mdl_title.setText(fields.getTitle().getStringValue());
    		MPArating_label.setText(fields.getMpRating().getStringValue());
    		duration_label.setText(movie.getDuration());
    		ytTrailerPlayerArea.getEngine().load(fields.getYtTrailerUrl().getStringValue());
    		description_label.setText(fields.getDescription().getStringValue());
    		director_label.setText(fields.getDirector().getStringValue());
    	} else {
    		mdl_cover.setImage(blankCover);
    		mdl_title.setText(defaultText);
    		ytTrailerPlayerArea.getEngine().load(null);
    	}
	}
    
    public void setCinemaDisplay(Node cinemaDisplayRootNode)
    {
    	if(cinemaDisplayContainer.getChildren().isEmpty())
    		cinemaDisplayContainer.getChildren().add(cinemaDisplayRootNode);
    }
    
    @FXML
    void likeMovieCallback(ActionEvent event) {
		System.out.println((Node)event.getSource());
    }
    
    @FXML
    void toggleDetailsCallback(ActionEvent event) {
    	Hyperlink link = (Hyperlink)event.getSource();
    	expantionState = (expantionState + 1) % 2;
    	link.setText(detailsText[expantionState]);
    	vbox_accordion.setMaxHeight(modalMaxSize[expantionState]);
    	
    	more_details_container.setVisible(expantionState == 1);
    }

}
