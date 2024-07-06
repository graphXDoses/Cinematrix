package com.texnologia_logismikou.Cinematrix.Views;

import java.util.List;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Controllers.AllMoviesViewController;
import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;
import com.texnologia_logismikou.Cinematrix.Users.Admin;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderAdmin;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderGuest;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderUser;

public class AllMoviesView
extends View<AllMoviesViewController>
implements VisibleUnderGuest, VisibleUnderUser, VisibleUnderAdmin
{
	public AllMoviesView()
	{
		super.loadFXML("AllMoviesView");
	}

	void standardPrepare()
	{
		getController().clearAll();
		
		List<Movie> movies = CinematrixAPI.getInstance().getMovies();
		
		if(!movies.isEmpty())
		{
			getController().appendNowFeaturing(movies.get(0).getModal());
			getController().appendNowFeaturing(movies.get(1).getModal());
			
			getController().appendUpcomming(movies.get(2).getModal());
		}
		for(int i=0; i<5; i++)
			getController().appendNowFeaturing(new MovieModal(null));
	}

	@Override
	public void showToAdmin()
	{
		getController().clearAll();
		getController().appendNowFeaturing(new MovieModal(null));
		
		List<Movie> movies = CinematrixAPI.getInstance().getMovies();
		
		if(!movies.isEmpty())
		{
			getController().appendNowFeaturing(movies.get(0).getModal());
			getController().appendNowFeaturing(movies.get(1).getModal());
			
			getController().appendUpcomming(movies.get(2).getModal());
		}
	}

	@Override
	public void showToUser()
	{
		standardPrepare();
	}

	@Override
	public void showToGuest()
	{
		standardPrepare();
	}
}
