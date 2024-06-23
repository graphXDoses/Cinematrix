package com.texnologia_logismikou.Cinematrix.Views;

import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Controllers.MovieDetailsViewController;

public class MovieDetailsView extends View<MovieDetailsViewController>
{
	private Movie selectedMovie = null;
	
	public MovieDetailsView()
	{
		super.loadFXML("MovieDetailsView");
	}

	@Override
	public void prepare()
	{
		getController().setData(selectedMovie);
	}
	
	public void setSelectedMovie(Movie movie)
	{
		selectedMovie = movie;
	}

}
