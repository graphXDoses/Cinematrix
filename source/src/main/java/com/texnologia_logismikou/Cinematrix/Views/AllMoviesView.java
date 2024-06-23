package com.texnologia_logismikou.Cinematrix.Views;

import java.util.List;

import com.texnologia_logismikou.Cinematrix.CinemaSystem;
import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Controllers.AllMoviesViewController;
import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;
import com.texnologia_logismikou.Cinematrix.Users.Admin;

public class AllMoviesView extends View<AllMoviesViewController>
{
	public AllMoviesView()
	{
		super.loadFXML("AllMoviesView");
	}

	@Override
	public void prepare()
	{
		getController().clearAll();
		
		if(CinemaSystem.getInstance().getCurrentUser() instanceof Admin)
			getController().appendNowFeaturing(new MovieModal(null));
		
		List<Movie> movies = CinemaSystem.getInstance().getMovies();
		
		if(!movies.isEmpty())
		{
			getController().appendNowFeaturing(movies.get(0).getModal());
			getController().appendNowFeaturing(movies.get(1).getModal());
			
			getController().appendUpcomming(movies.get(2).getModal());
		}
	}
}
