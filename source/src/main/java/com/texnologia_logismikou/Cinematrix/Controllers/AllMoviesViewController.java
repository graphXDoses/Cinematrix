package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;


public class AllMoviesViewController{

    @FXML private HBox now_featuring_modals;
    @FXML private HBox upcomming_modals;
    
//    private List<Movie> movies;
//    private Parent root;
	
	@FXML
	void initialize()
	{
		/*
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
			
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("screens/MovieModal.fxml"));
			root = fxmlLoader.load();
			
			MovieModalController controller = fxmlLoader.getController();
			controller.setData(new Movie("images/_Napoleon_Cover.jpg"));
			
			upcomming_modals.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
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
	
	/*
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
	*/

}
