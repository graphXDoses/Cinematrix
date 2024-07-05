package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;


public class AllMoviesViewController{

    @FXML private HBox now_featuring_container;
    @FXML private HBox upcomming_container;
	
	@FXML void initialize() { }
	
	public void clearAll()
	{
		now_featuring_container.getChildren().clear();
		upcomming_container.getChildren().clear();
	}
	
	public void appendNowFeaturing(MovieModal movie)
	{
		now_featuring_container.getChildren().add(movie.getParent());
	}
	
	public void appendUpcomming(MovieModal movie)
	{
		upcomming_container.getChildren().add(movie.getParent());
	}

}
