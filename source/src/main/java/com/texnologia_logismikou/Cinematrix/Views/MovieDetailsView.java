package com.texnologia_logismikou.Cinematrix.Views;

import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Controllers.MovieDetailsViewController;
import com.texnologia_logismikou.Cinematrix.Managers.AvailableCinemasDisplay;

public class MovieDetailsView extends View<MovieDetailsViewController>
{
	private Movie selectedMovie = null;
	private AvailableCinemasDisplay cinemasDisplay;
	
	public MovieDetailsView()
	{
		super.loadFXML("MovieDetailsView");
		
		cinemasDisplay = new AvailableCinemasDisplay();
	}

	@Override
	public void prepare()
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
}
