package com.texnologia_logismikou.Cinematrix;

import java.util.ArrayList;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.Managers.MainDisplay;
import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;
import com.texnologia_logismikou.Cinematrix.Users.Admin;
import com.texnologia_logismikou.Cinematrix.Users.Guest;
import com.texnologia_logismikou.Cinematrix.Users.User;
import com.texnologia_logismikou.Cinematrix.Views.AllMoviesView;
import com.texnologia_logismikou.Cinematrix.Views.MovieDetailsView;
import com.texnologia_logismikou.Cinematrix.Views.NearCinemasView;
import com.texnologia_logismikou.Cinematrix.Views.SignUpView;

public class CinemaSystem {
	private static CinemaSystem instance = null;
	
	private static User currentUser;
	private static MainDisplay mD;
	private static List<Movie>   movies   = new ArrayList<>();
	private static List<Cinema>  cinemas  = new ArrayList<>();
	private static List<Context> contexts = new ArrayList<>();
	private static Context activeContext;
	
	private CinemaSystem()
	{
//		currentUser = new Admin();
		currentUser = new Guest();
		
		mD = new MainDisplay();
		
		contexts.add(new Context("Movies", "images/movie.png", new AllMoviesView(), new MovieDetailsView()));
		contexts.add(new Context("Cinemas", "images/theater.png", new NearCinemasView()));
		contexts.add(new Context("Account", "images/account.png", new SignUpView()));
		
	}
	
	public MainDisplay getMainDisplay() { return(instance.mD); }
	public List<Movie> getMovies() { return(instance.movies); }
	public List<Cinema> getCinemas() { return(instance.cinemas); }
	public List<Context> getContexts() { return(instance.contexts); }
	public Context     getActiveContext() { return(instance.activeContext); }
	
	public void setActiveContext(Context ctx)
	{
		activeContext = ctx;
		
		contexts.forEach(context->{ context.getButton().getController().deactivate(); });
		activeContext.getButton().getController().activate();
		
		getMainDisplay().refresh();
	}
	
	public static CinemaSystem getInstance()
	{
		if(instance == null)
			instance = new CinemaSystem();
		return(instance);
	}

	public User getCurrentUser() { return(currentUser); }
	
	public void fetchMoviesFromDatabase()
	{
		movies.add(new Movie(
			"Perfect Blue",
			"PerfectBlue",
			1997,
			"https://www.youtube.com/embed/BD8I4v9U4mw?si=3g8BfKh8nhHoXL0U"
		));
		movies.add(new Movie(
			"Rush Hour",
			"RushHour",
			1998,
			"https://www.youtube.com/embed/JMiFsFQcFLE?si=2u52cDgmAiSpxO1-"
		));
		movies.add(new Movie(
			"Napoleon",
			"Napoleon",
			2023,
			"https://www.youtube.com/embed/OAZWXUkrjPc?si=apRRZD9yjPYtIZQ3"
		));
	}
	
	public void fetchCinemasFromDatabase()
	{
		cinemas.add(new Cinema(
			"Regal Gallery Place & 4DX",
			"701 Seventh Street NW, Washington, DC 20001",
			0.81f
		));
		cinemas.add(new Cinema(
			"Cinema Vakoura",
			"Michail Ioannou 8, Thessaloniki 546 22",
			1.2f
		));
	}
}
