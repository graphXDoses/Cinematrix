package com.texnologia_logismikou.Cinematrix.Views;

import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Controllers.MovieDetailsViewController;
import com.texnologia_logismikou.Cinematrix.Managers.AvailableCinemasDisplay;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderAdmin;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderGuest;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderUser;

public class MovieDetailsView
extends View<MovieDetailsViewController>
implements VisibleUnderGuest, VisibleUnderUser, VisibleUnderAdmin
{
	private Movie selectedMovie = null;
	private AvailableCinemasDisplay cinemasDisplay;
	
	public MovieDetailsView()
	{
		super.loadFXML("MovieDetailsView");
		
		cinemasDisplay = new AvailableCinemasDisplay();
	}

	void standardPrepare()
	{
		getController().setMovieDetailData(selectedMovie);
		getController().setCinemaDisplay(cinemasDisplay.getParent());
		
		cinemasDisplay.refresh();
	}
	
	public void setSelectedMovie(Movie movie)
	{
		selectedMovie = movie;
	}
	
	public Movie getSelectedMovie()
	{
		return(selectedMovie);
	}

	@Override
	public void showToAdmin()
	{
		standardPrepare();
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
