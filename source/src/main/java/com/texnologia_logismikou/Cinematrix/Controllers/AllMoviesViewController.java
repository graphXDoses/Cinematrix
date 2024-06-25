package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;


public class AllMoviesViewController{

    @FXML private HBox now_featuring_modals;
    @FXML private HBox upcomming_modals;
	
	@FXML void initialize() { }
	
	public void clearAll()
	{
		now_featuring_modals.getChildren().clear();
		upcomming_modals.getChildren().clear();
	}
	
	public void appendNowFeaturing(MovieModal movie)
	{
		now_featuring_modals.getChildren().add(movie.getParent());
	}
	
	public void appendUpcomming(MovieModal movie)
	{
		upcomming_modals.getChildren().add(movie.getParent());
	}

}
