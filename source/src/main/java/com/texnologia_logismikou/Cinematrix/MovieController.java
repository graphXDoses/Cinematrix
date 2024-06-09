package com.texnologia_logismikou.Cinematrix;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MovieController implements Initializable {

	private List<Movie> movies;
	@FXML
    private HBox now_featuring_modals;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		movies = new ArrayList<Movie>(getMovies());
		
		try {
			for(Movie movie: movies)
			{
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(MovieController.class.getResource("screens/" + "MovieModal" + ".fxml"));
				Pane pane = fxmlLoader.load();
				MovieModalController controller = fxmlLoader.getController();
				controller.setData(movie);
				
				//HBox nowMovieStack = fxmlLoader.load();
				now_featuring_modals.getChildren().add(pane);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List<Movie> getMovies()
	{
		List<Movie> ls = new ArrayList<Movie>();
		
		Movie movie;
		
		movie = new Movie("image/_PerfectBlue_Cover.jpg");
		ls.add(movie);
		
		movie = new Movie("image/_RushHour_Cover.jpg");
		ls.add(movie);
		
		return(ls);
	}

}
