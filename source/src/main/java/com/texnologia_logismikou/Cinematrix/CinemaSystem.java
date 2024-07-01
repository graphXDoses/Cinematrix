package com.texnologia_logismikou.Cinematrix;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.*;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.*;
import com.texnologia_logismikou.Cinematrix.Managers.MainDisplay;
import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;
import com.texnologia_logismikou.Cinematrix.ResponseBodies.*;
import com.texnologia_logismikou.Cinematrix.Users.Admin;
import com.texnologia_logismikou.Cinematrix.Users.Guest;
import com.texnologia_logismikou.Cinematrix.Users.User;
import com.texnologia_logismikou.Cinematrix.Views.AllMoviesView;
import com.texnologia_logismikou.Cinematrix.Views.MovieDetailsView;
import com.texnologia_logismikou.Cinematrix.Views.NearCinemasView;
import com.texnologia_logismikou.Cinematrix.Views.SeatSelectionView;
import com.texnologia_logismikou.Cinematrix.Views.SignUpView;

public class CinemaSystem {
	private static CinemaSystem instance = null;
	
	private static User currentUser;
	private static MainDisplay mD;
	private static List<Movie>   movies   = new ArrayList<>();
	private static List<Cinema>  cinemas  = new ArrayList<>();
	private static List<Context> contexts = new ArrayList<>();
	private static Context activeContext;
	
	private final String webKey = "AIzaSyDTn8MSxkAuIX-sH-_I_vwAwVqIt77sORU";
	
	private CinemaSystem()
	{
		currentUser = new Admin();
//		currentUser = new Guest();
		
		mD = new MainDisplay();
		
		contexts.add(new Context("Movies", "images/movie.png", new AllMoviesView(), new MovieDetailsView(), new SeatSelectionView()));
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
			"R",
			81,
			"https://www.youtube.com/embed/BD8I4v9U4mw?si=3g8BfKh8nhHoXL0U",
			"A pop singer gives up her career to become an actress, but she slowly goes insane when she starts being stalked by an obsessed fan and what seems to be a ghost of her past.",
			"Satoshi Kon"
		));
		movies.add(new Movie(
			"Rush Hour",
			"RushHour",
			1998,
			"PG-13",
			98,
			"https://www.youtube.com/embed/JMiFsFQcFLE?si=2u52cDgmAiSpxO1-",
			"A loyal and dedicated Hong Kong Inspector teams up with a reckless and loudmouthed L.A.P.D. detective to rescue the Chinese Consul's kidnapped daughter, while trying to arrest a dangerous crime lord along the way.",
			"Brett Ratner"
		));
		movies.add(new Movie(
			"Napoleon",
			"Napoleon",
			2023,
			"R",
			158,
			"https://www.youtube.com/embed/OAZWXUkrjPc?si=apRRZD9yjPYtIZQ3",
			"An epic that details the chequered rise and fall of French Emperor Napoleon Bonaparte and his relentless journey to power through the prism of his addictive, volatile relationship with his wife, Josephine.",
			"Ridley Scott"
		));
		
		// Screenings
		cinemas.get(0).setMovieScreening(
				movies.get(0),
				null,
				null,
				new ArrayList<>(Arrays.asList("10:25p", "9:30a"))
		);
		cinemas.get(1).setMovieScreening(
				movies.get(1),
				null,
				null,
				new ArrayList<>(Arrays.asList("6:30p", "9:00p", "10:00p", "11:00p"))
		);
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
	
	public void userSignUp(String name, String email, String password, boolean isAdmin) {
		
		SignUpResponseBody signUpResponse = new SignUpResponseBody();
		
		// Return on exception and send user back to home screen?
		try {
			signUpResponse = RequestHandler.getInstance(webKey).SignUpRequest(email, password);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			// return;
		} catch (InterruptedException e) {
			e.printStackTrace();
			// return;
		} catch (IOException e) {
			e.printStackTrace();
			// return;
		}
		
		// If error occurs show appropriate message and return?
		if(signUpResponse.getError() != null) {
			System.out.println("Error signing up. Details: " + signUpResponse.getError().getMessage());
			// return;
		} else {
			System.out.println("User succesfully signed up with uid: " + signUpResponse.getLocalId());
		}
		
		/*
		 *  After the sign up process store locally some useful information like Firebase ID, User ID, Name, Email etc.
		 */
		
		UserDocument createDocResponse = new UserDocument();
		
		try {
			createDocResponse = RequestHandler.getInstance(webKey).createUserDocumentRequest(signUpResponse.getLocalId(), signUpResponse.getIdToken());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			// return;
		} catch (InterruptedException e) {
			e.printStackTrace();
			// return;
		} catch (IOException e) {
			e.printStackTrace();
			// return;
		}
		
		if(createDocResponse.getError() != null) {
			System.out.println("Error creating user document. Details: " + createDocResponse.getError().getMessage());
		} else {
			System.out.println("User document succefully created at: " + createDocResponse.getCreateTime());
		}
		
		UserDocument initializeDocResponse = new UserDocument();
		UserFields fields = new UserFields(name , signUpResponse.getEmail(), isAdmin); // <--- Email and Name goes here, but it can be expanded to receive more user fields like admin.
		
		System.out.println("User admin field is: " + fields.getAdmin().getBooleanValue());
		
		try {
			initializeDocResponse = RequestHandler.getInstance(webKey).initializeUserDocumentRequest(signUpResponse.getLocalId(), signUpResponse.getIdToken(), fields);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			// return;
		} catch (InterruptedException e) {
			e.printStackTrace();
			// return;
		} catch (IOException e) {
			e.printStackTrace();
			// return;
		}
		
		if(initializeDocResponse.getError() != null) {
			System.out.println("Error initializing user document. Details: " + initializeDocResponse.getError().getMessage());
		} else {
			System.out.println("User document succesfully initialized at: " + initializeDocResponse.getUpdateTime());
		}
	}
}
