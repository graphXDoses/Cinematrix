package com.texnologia_logismikou.Cinematrix.Views;

import java.util.ArrayList;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Controllers.MovieDetailsViewController;
import com.texnologia_logismikou.Cinematrix.Managers.AvailableCinemasDisplay;
import com.texnologia_logismikou.Cinematrix.Managers.ScreeningDaySelectionButtonWidget;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderAdmin;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderGuest;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderUser;

public class MovieDetailsView
extends View<MovieDetailsViewController>
implements VisibleUnderGuest, VisibleUnderUser, VisibleUnderAdmin
{
	private Movie selectedMovie = null;
	private AvailableCinemasDisplay cinemasDisplay;
	private List<ScreeningDaySelectionButtonWidget> buttons = new ArrayList<ScreeningDaySelectionButtonWidget>();
	
	public MovieDetailsView()
	{
		super.loadFXML("MovieDetailsView");
		
		cinemasDisplay = new AvailableCinemasDisplay();
	}

	void standardPrepare()
	{
		getController().setLikable(false);
		getController().setMovieDetailData(selectedMovie);
		buttons = getController().setAvailableDays(selectedMovie.getAssociateScreenings());
		getController().setCinemaDisplay(cinemasDisplay.getParent());
		defaultSelectFilters();
		
		cinemasDisplay.refresh();
	}
	
	public void defaultSelectFilters()
	{
		buttons.get(0).getController().selectThis();
		getController().enforceDefaultFiltering();
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
		getController().setLikable(true);
	}

	@Override
	public void showToGuest()
	{
		standardPrepare();
	}
}
