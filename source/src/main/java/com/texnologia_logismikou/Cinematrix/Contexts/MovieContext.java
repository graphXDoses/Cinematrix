package com.texnologia_logismikou.Cinematrix.Contexts;

import com.texnologia_logismikou.Cinematrix.Views.AllMoviesView;
import com.texnologia_logismikou.Cinematrix.Views.MovieDetailsView;
import com.texnologia_logismikou.Cinematrix.Views.SeatSelectionView;
import com.texnologia_logismikou.Cinematrix.Views.TicketRevisionView;
import com.texnologia_logismikou.Cinematrix.Views.View;

public class MovieContext extends Context
{
	public static AllMoviesView ALL_MOVIES_VIEW = new AllMoviesView();
	public static MovieDetailsView MOVIE_DETAILS_VIEW = new MovieDetailsView();
	public static SeatSelectionView SEAT_SELECTION_VIEW = null;//new SeatSelectionView();
	public static TicketRevisionView TICKET_REVISION_VIEW = null;
	
	public MovieContext()
	{
		super("Movies", "images/movie.png");
		this.defaultView = ALL_MOVIES_VIEW;
		this.activeView  = this.defaultView;
	}

}
