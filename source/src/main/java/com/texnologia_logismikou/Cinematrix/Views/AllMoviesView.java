package com.texnologia_logismikou.Cinematrix.Views;

import com.texnologia_logismikou.Cinematrix.CinemaSystem;
import com.texnologia_logismikou.Cinematrix.Controllers.AllMoviesViewController;

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
		
		getController().appendNowFeaturing(CinemaSystem.Invoke().getMovies().get(0));
		getController().appendNowFeaturing(CinemaSystem.Invoke().getMovies().get(1));
		
		getController().appendUpcomming(CinemaSystem.Invoke().getMovies().get(2));
	}
}
