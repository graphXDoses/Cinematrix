package com.texnologia_logismikou.Cinematrix;

import java.util.ArrayList;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.Managers.MainDisplay;
import com.texnologia_logismikou.Cinematrix.Managers.Movie;
import com.texnologia_logismikou.Cinematrix.Users.Admin;
import com.texnologia_logismikou.Cinematrix.Users.Guest;
import com.texnologia_logismikou.Cinematrix.Users.User;
import com.texnologia_logismikou.Cinematrix.Views.AllMoviesView;
import com.texnologia_logismikou.Cinematrix.Views.MovieDetailsView;
import com.texnologia_logismikou.Cinematrix.Views.SignUpView;

public class CinemaSystem {
	private static CinemaSystem instance = null;
	
	private static User currentUser;
	private static MainDisplay mD;
	private static List<Movie>   movies = new ArrayList<>();
	private static List<Context> contexts = new ArrayList<>();
	private static Context activeContext;
	
	private CinemaSystem()
	{
		currentUser = new Admin();
		
		mD = new MainDisplay();
		
		movies.add(new Movie("images/_PerfectBlue_Cover.jpg"));
		movies.add(new Movie("images/_RushHour_Cover.jpg"));
		movies.add(new Movie("images/_Napoleon_Cover.jpg"));
		
		contexts.add(new Context("Movies", "images/movie.png", new AllMoviesView(), new MovieDetailsView()));
		contexts.add(new Context("Cinemas", "images/theater.png", new MovieDetailsView()));
		contexts.add(new Context("Account", "images/account.png", new SignUpView()));
		
	}
	
	public MainDisplay getMainDisplay() { return(instance.mD); }
	public List<Movie> getMovies() { return(instance.movies); }
	public List<Context> getContexts() { return(instance.contexts); }
	public Context     getActiveContext() { return(instance.activeContext); }
	
	public void setActiveContext(Context ctx)
	{
		activeContext = ctx;
		
		contexts.forEach((context) -> { context.getButton().getController().deactivate(); });
		activeContext.getButton().getController().activate();
		
		getMainDisplay().refresh();
	}
	
	public static CinemaSystem Invoke()
	{
		if(instance == null)
			instance = new CinemaSystem();
		return(instance);
	}

	public static User getCurrentUser() { return(currentUser); }
}
