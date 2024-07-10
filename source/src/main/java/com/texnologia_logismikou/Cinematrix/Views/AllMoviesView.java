package com.texnologia_logismikou.Cinematrix.Views;

import java.time.LocalDateTime;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Screening;
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

	//TODO Movie filtering based on Screenings.

	void standardPrepare()
	{
		getController().clearAll();
		getController().revealAdminSection();
		
		List<Screening> screenings = CinematrixAPI.getInstance().getScreenings();
		
		if(!screenings.isEmpty())
		{
			// Fill NowFeaturing container
			screenings.stream().filter(screening->{
				return(screening.getHours().stream().filter(hour->{
					return(hour.isAfter(LocalDateTime.of(2024, 7, 14, 0, 0)));
				}).toList().isEmpty());
			}).forEach(screening->{
				getController().appendNowFeaturing(screening.getMovie().getModal());
			});

			// Fill Upcomming container
			screenings.stream().filter(screening->{
				return(screening.getHours().stream().filter(hour->{
					return(hour.isBefore(LocalDateTime.of(2024, 7, 14, 0, 0)));
				}).toList().isEmpty());
			}).forEach(screening->{
				getController().appendUpcomming(screening.getMovie().getModal());
			});
		}
	}

	@Override
	public void showToAdmin()
	{
		getController().clearAll();
		getController().revealAdminSection();
		
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
