package com.texnologia_logismikou.Cinematrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;


public class MovieController{

    @FXML
    private HBox now_featuring_modals;
    
    private List<Movie> movies;
    private Parent root;

	public void sceneGenerator(String fxml) throws IOException {
		root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("screens/" + fxml + ".fxml")));
		System.out.println(root.toString());
	}
	
	@FXML
	void initialize()
	{
		assert now_featuring_modals != null : "fx:id \'now_featuring_modals\' was not injected";
		movies = new ArrayList<Movie>(getMovies());
		
		try {
//			sceneGenerator("MovieModal");
			for(Movie movie: movies)
			{
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("screens/MovieModal.fxml"));
				root = fxmlLoader.load();
				
				MovieModalController controller = fxmlLoader.getController();
				controller.setData(movie);
				
				now_featuring_modals.getChildren().add(root);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	List<Movie> getMovies()
	{
		List<Movie> ls = new ArrayList<>();
		Movie movie;
		
		movie = new Movie("images/_PerfectBlue_Cover.jpg");
		ls.add(movie);
		
		movie = new Movie("images/_RushHour_Cover.jpg");
		ls.add(movie);
		
		return(ls);
	}

}
