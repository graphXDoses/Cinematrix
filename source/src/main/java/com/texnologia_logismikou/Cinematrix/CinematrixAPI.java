package com.texnologia_logismikou.Cinematrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.Contexts.AccountContext;
import com.texnologia_logismikou.Cinematrix.Contexts.CinemaContext;
import com.texnologia_logismikou.Cinematrix.Contexts.Context;
import com.texnologia_logismikou.Cinematrix.Contexts.MovieContext;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.*;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.*;
import com.texnologia_logismikou.Cinematrix.ResponseBodies.*;
import com.texnologia_logismikou.Cinematrix.Managers.MainDisplay;
import com.texnologia_logismikou.Cinematrix.Managers.MainUI;
import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;
import com.texnologia_logismikou.Cinematrix.Users.Admin;
import com.texnologia_logismikou.Cinematrix.Users.Guest;
import com.texnologia_logismikou.Cinematrix.Users.UserCore;
import com.texnologia_logismikou.Cinematrix.Views.AllMoviesView;
import com.texnologia_logismikou.Cinematrix.Views.MovieDetailsView;
import com.texnologia_logismikou.Cinematrix.Views.NearCinemasView;
import com.texnologia_logismikou.Cinematrix.Views.SeatSelectionView;
import com.texnologia_logismikou.Cinematrix.Views.SignUpView;
import com.texnologia_logismikou.Cinematrix.Views.UserDashboardView;
import com.texnologia_logismikou.Cinematrix.Views.View;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.nio.file.Path;
import com.texnologia_logismikou.Cinematrix.Views.LoginView;

public class CinematrixAPI {
	private static CinematrixAPI instance = null;
	
	private final String webKey = "AIzaSyDTn8MSxkAuIX-sH-_I_vwAwVqIt77sORU";
	public final static String imagesPath = System.getenv("APPDATA") + "/Cinematrix/images";
	
	private static UserCore currentUser;
	private static MainUI UI;
	private static List<Movie>   movies   = new ArrayList<>();
	private static List<Cinema>  cinemas  = new ArrayList<>();
	private static List<Screening>  screenings  = new ArrayList<>();
	private static List<Context> contexts = new ArrayList<>();
	private static Context activeContext;
	
	public static final MovieContext   MOVIE_CONTEXT   = new MovieContext();
	public static final CinemaContext  CINEMA_CONTEXT  = new CinemaContext();
	public static final AccountContext ACCOUNT_CONTEXT = new AccountContext();

	private CinematrixAPI()
	{
		currentUser = new Guest();
		
		contexts.add(MOVIE_CONTEXT);
		contexts.add(CINEMA_CONTEXT);
		contexts.add(ACCOUNT_CONTEXT);
		
	}
	
	public MainDisplay getMainDisplay() { return(instance.UI.MAIN_DISPLAY); }
	public List<Movie> getMovies() { return(instance.movies); }
	public List<Cinema> getCinemas() { return(instance.cinemas); }
	public List<Screening> getScreenings() { return(instance.screenings); }
	public List<Context> getContexts() { return(instance.contexts); }
	public Context     getActiveContext() { return(instance.activeContext); }
	
	public void makeCinematrixDir() {
		
		File cinematrixDir = new File(System.getenv("APPDATA") + "/Cinematrix");
		
		if(!cinematrixDir.isDirectory()) {
			boolean temp = cinematrixDir.mkdir();
			if(!temp) {
				System.out.println("Error creating cinematrix folder!");
				return;
			}
		}
		
    	File imagesDir = new File(CinematrixAPI.imagesPath);
    	
    	if(!imagesDir.isDirectory()) {
    		boolean temp = imagesDir.mkdir();
    		if(!temp) {
    			System.out.println("Error creating images folder!");
    		}
    	}
    }
	
	public void setActiveContext(Context ctx)
	{
		activeContext = ctx;
		
		contexts.forEach(context->{ context.getButton().getController().deactivate(); });
		activeContext.getButton().getController().activate();
		
		if(UI != null)
			UI.reemplaceSearchBar();
		getMainDisplay().refresh();
	}
	
	public static CinematrixAPI getInstance()
	{
		if(instance == null)
			instance = new CinematrixAPI();
		return(instance);
	}

	public UserCore getCurrentUser() { return(currentUser); }

	public void setCurrentUser(UserCore user)
	{
		if(user != null)
		{
			currentUser = user;
			UI.FOOTERBAR.updateUserTypeDisplay();
		}	
	}

	public void userSignUp(String name, String email, String password) throws SignUpException {
		
		if(name.isEmpty()) {
			throw new SignUpException("EMPTY_NAME", null);
		}
		
		SignUpResponseBody signUpResponse = new SignUpResponseBody();
		
		// Return on exception and send user back to home screen?
		try {
			signUpResponse = RequestHandler.getInstance().SignUpRequest(email, password, webKey);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new SignUpException("Internal error occured. Please try again later.", e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new SignUpException("Internal error occured. Please try again later.", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SignUpException("Internal error occured. Please try again later.", e);
		}
		
		// If error occurs show appropriate message and return?
		if(signUpResponse.getError() != null) {
			throw new SignUpException(signUpResponse.getError().getMessage(), null);
		} else {
			System.out.println("User succesfully signed up with uid: " + signUpResponse.getLocalId());
		}
		
		/*
		 *  After the sign up process store locally some useful information like Firebase ID, User ID, Name, Email etc.
		 */
		
		UserDocument createDocResponse = new UserDocument();
		
		try {
			createDocResponse = RequestHandler.getInstance().createUserDocumentRequest(signUpResponse.getLocalId(), signUpResponse.getIdToken());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new SignUpException("Internal error occured. Please try again later.", e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new SignUpException("Internal error occured. Please try again later.", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SignUpException("Internal error occured. Please try again later.", e);
		}
		
		if(createDocResponse.getError() != null) {
			try {
				// Delete the user account.
				RequestHandler.getInstance().deleteUserAccountRequest(signUpResponse.getIdToken(), webKey);
			} catch (Exception e) {
				e.printStackTrace();
				throw new SignUpException("Internal error occured. Please try again later.", e);
			}
			throw new SignUpException(createDocResponse.getError().getMessage(), null);
		} else {
			System.out.println("User document succefully created at: " + createDocResponse.getCreateTime());
		}
		
		UserDocument initializeDocResponse = new UserDocument();
		UserFields fields = new UserFields(name , signUpResponse.getEmail(), false); // <--- Email and Name goes here, but it can be expanded to receive more user fields like admin.
		
		System.out.println("User admin field is: " + fields.getAdmin().getBooleanValue());
		
		try {
			initializeDocResponse = RequestHandler.getInstance().updateUserDocumentRequest(signUpResponse.getLocalId(), signUpResponse.getIdToken(), fields);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new SignUpException("Internal error occured. Please try again later.", e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new SignUpException("Internal error occured. Please try again later.", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SignUpException("Internal error occured. Please try again later.", e);
		}
		
		if(initializeDocResponse.getError() != null) {
			try {
				// Delete the user account.
				RequestHandler.getInstance().deleteUserAccountRequest(signUpResponse.getIdToken(), webKey);
				RequestHandler.getInstance().deleteUserDocumentRequest(signUpResponse.getIdToken(), signUpResponse.getLocalId());
			} catch (Exception e) {
				e.printStackTrace();
				throw new SignUpException("Internal error occured. Please try again later.", e);
			}
			throw new SignUpException(initializeDocResponse.getError().getMessage(), null);
		} else {
			System.out.println("User document succesfully initialized at: " + initializeDocResponse.getUpdateTime());
		}
		
	}
	
	public String userSignIn(String email, String password) throws SignInException {
		
		SignInResponseBody signInResponse = new SignInResponseBody();
		UserDocument userDoc = new UserDocument();
		
		try {
			signInResponse = RequestHandler.getInstance().signInRequest(email, password, webKey);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new SignInException("Internal error occured. Please try again later.", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SignInException("Internal error occured. Please try again later.", e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new SignInException("Internal error occured. Please try again later.", e);
		}
		
		if(signInResponse.getError() != null) {
			throw new SignInException(signInResponse.getError().getMessage(), null);
		}
		
		try {
			userDoc = RequestHandler.getInstance().getUserDocumentRequest(signInResponse.getLocalId(), signInResponse.getIdToken());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/*
		 *  Store the Firebase ID, User ID and other useful information for later use.
		 */
		CinematrixAPI.getInstance().setCurrentUser(UserCore.createUser(userDoc));
		
		return signInResponse.getIdToken();
	}
	
	/** 
	 * @deprecated Use createMovie instead.
	 */
	public void createMovieDocument(String name, String firebaseId) {
		
		MovieDocument createResponse = new MovieDocument();
		
		try {
			createResponse = RequestHandler.getInstance().createMovieDocumentRequest(name, firebaseId);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			// return;
		} catch (IOException e) {
			e.printStackTrace();
			// return;
		} catch (InterruptedException e) {
			e.printStackTrace();
			// return;
		}
		
		if(createResponse.getError() != null) {
			System.out.println(createResponse.getError().getMessage());
			// return;
		} else {
			System.out.println("Movie Document succesfully created at: " + createResponse.getCreateTime());
		}
	}
	
	public void createMovie(MovieFields fields, String firebaseId) {
		
		MovieDocument response = new MovieDocument();
		
		try {
			response = RequestHandler.getInstance().updateMovieDocumentRequest(fields, firebaseId);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			// return;
		} catch (IOException e) {
			e.printStackTrace();
			// return;
		} catch (InterruptedException e) {
			e.printStackTrace();
			// return;
		}
		
		if(response.getError() != null) {
			System.out.println(response.getError().getMessage());
		} else {
			System.out.println("Movie Document succesfully updated at: " + response.getUpdateTime());
		}
	}

	public void deleteMovieDocument(String name) {
		
		int statusCode;
		
		try {
			statusCode = RequestHandler.getInstance().deleteMovieDocumentRequest(name);
			System.out.println("STATUS CODE: " + statusCode);
			switch(statusCode) {
			case 200: System.out.println("Movie document deleted succsefully."); break;
			default: System.out.println("Couldn't delete movie document.");
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void createCinema(String firebaseId, CinemaFields fields) {
		
		CinemaDocument response = new CinemaDocument();
		
		try {
			response = RequestHandler.getInstance().updateCinemaDocumentRequest(fields, firebaseId);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(response.getError() != null) {
			System.out.println("Error creating Cinema document. Error details: " + response.getError().getMessage());
			return;
		}
		
		if(response.getError() != null) {
			System.out.println(response.getError().getMessage());
		} else {
			System.out.println("Cinema Document succesfully updated at: " + response.getUpdateTime());
		}
	}

	public void createVenue(String firebaseId, VenueFields fields, String cinemaName) {
		
		VenueDocument response = new VenueDocument();
		
		try {
			response = RequestHandler.getInstance().updateVenueDocumentRequest(firebaseId, fields, cinemaName);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(response.getError() != null) {
			System.out.println(response.getError().getMessage());
		} else {
			System.out.println("Venue doc created!");
		}
		
		Venue venue = new Venue(response);
		
		for(Cinema i: cinemas) {
			if(i.getDoc().getFields().getName().getStringValue().equals(cinemaName)) {
				i.getVenues().add(venue);
			}
		}
	}

	public void createScreening(ScreeningFields fields, String firebaseId) {
		
		ScreeningDocument response = new ScreeningDocument();
		
		try {
			response = RequestHandler.getInstance().createScreeningDocumentRequest(firebaseId, fields);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(response.getError() != null) {
			System.out.println(response.getError().getMessage());
		} else {
			System.out.println("Screening doc created at: " + response.getCreateTime());
		}
		
	}

	public void fetchCinemasFromDatabase() {
		
		ListCinemasResponseBody cinemasList = new ListCinemasResponseBody();
		
		try {
			cinemasList = RequestHandler.getInstance().fetchAllCinemasRequest();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(cinemasList.getError() != null) {
			System.out.println(cinemasList.getError().getMessage());
		}
			
		for(CinemaDocument cinema: cinemasList.getDocuments()) {
		
			ListVenuesResponseBody venuesList = new ListVenuesResponseBody();
			try {
				venuesList = RequestHandler.getInstance().fetchAllCinemaVenuesRequest(StringField.toPascalCase(cinema.getFields().getName().getStringValue()));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			ArrayList<Venue> list = new ArrayList<Venue>();
			
			for(VenueDocument i: venuesList.getDocuments()) {
				
				Venue v = new Venue(i);
				list.add(v);
			}
			
			cinemas.add(new Cinema(cinema, list));
		}
	}
	
	public void fetchMoviesFromDatabase() {
		
		ListMoviesResponseBody moviesList = new ListMoviesResponseBody();
		
		try {
			moviesList = RequestHandler.getInstance().fetchAllMovies();
		} catch (URISyntaxException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(moviesList.getError() != null) {
			System.out.println(moviesList.getError().getMessage());
		}
		
		for(MovieDocument movie: moviesList.getDocuments()) {
						
			String movieName = StringField.toPascalCase(movie.getFields().getTitle().getStringValue());
			String imagePath = "/_" + movieName + "_Cover.jpg";
			File image = new File(CinematrixAPI.imagesPath + imagePath);
			
			if(!image.exists()) {
				
				try {
					StorageHandler.getInstance().downloadMovieImage(movieName);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			} 
			movies.add(new Movie(movie, movieName));
			System.out.println("Have the image!");
		}
		
		screenings.add(new Screening(
				movies.get(0),
				cinemas.get(1),
				new Venue(VenueTopdownType.TYPE_A, VenueSoundSystem.STANDARD_SYSTEM, VenueTraits.ACCESSIBILLITY_DEVICES_AVAILABLE),
				new ArrayList<>(Arrays.asList(
						LocalDateTime.of(2024, 7, 10, 20, 25),
						LocalDateTime.of(2024, 7, 10, 9, 30)
				))
		));
		screenings.add(new Screening(
				movies.get(1),
				cinemas.get(1),
				new Venue(VenueTopdownType.TYPE_A, VenueSoundSystem.STANDARD_SYSTEM, VenueTraits.RESERVED_SEATING),
				new ArrayList<>(Arrays.asList(
						LocalDateTime.of(2024, 7, 12, 18, 30),
						LocalDateTime.of(2024, 7, 12, 21, 0),
						LocalDateTime.of(2024, 7, 13, 22, 0),
						LocalDateTime.of(2024, 7, 13, 23, 0)
				))
		));
		screenings.add(new Screening(
				movies.get(2),
				cinemas.get(1),
				new Venue(VenueTopdownType.TYPE_B, VenueSoundSystem.STANDARD_SYSTEM, VenueTraits.RESERVED_SEATING),
				new ArrayList<>(Arrays.asList(
						LocalDateTime.of(2024, 9, 12, 18, 30),
						LocalDateTime.of(2024, 9, 12, 21, 0),
						LocalDateTime.of(2024, 9, 13, 22, 0)
				))
		));
		screenings.add(new Screening(
				movies.get(2),
				cinemas.get(0),
				new Venue(VenueTopdownType.TYPE_B, VenueSoundSystem.STANDARD_SYSTEM, VenueTraits.RESERVED_SEATING),
				new ArrayList<>(Arrays.asList(
						LocalDateTime.of(2024, 9, 12, 18, 30),
						LocalDateTime.of(2024, 9, 12, 21, 0),
						LocalDateTime.of(2024, 9, 13, 22, 0)
				))
		));
	}
	/*
	public void fetchScreeningsFromDatabase() {
		
		ListScreeningsResponseBody response = new ListScreeningsResponseBody();
		
		try {
			response = RequestHandler.getInstance().fetchAllScreenings();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(response.getError() != null) {
			System.out.println(response.getError().getMessage());
		}
		
		for(ScreeningDocument doc: response.getDocuments()) {
			
			Cinema theCinema = cinemas.stream().filter(cinema -> cinema.getDoc().getFields().getUid().getStringValue().equals(doc.getFields().getCinemaUid().getStringValue())).toList().get(0);
			Movie theMovie = movies.stream().filter(movie -> movie.getDoc().getFields().getUid().getStringValue().equals(doc.getFields().getMovieUid().getStringValue())).toList().get(0);
			Venue theVenue = null;
			
			for(Venue i: theCinema.getVenues()) {
				if(i.getDoc().getFields().getUid().getStringValue().equals(doc.getFields().getVenueUid().getStringValue())) {
					theVenue = i;
				}
			}
			
			System.out.println("The screening corresponds to Cinema: " + theCinema.getDoc().getFields().getName().getStringValue()
					+ " | and movie: " + theMovie.getDoc().getFields().getTitle().getStringValue()
					+ " | and venue: " + theVenue.getDoc().getFields().getName().getStringValue());
			
			//screenings.add(new Screening(theMovie, theCinema, theVenue, Arrays.asList("16", "30")));
		}
	}
	*/
	public void placeUIOnStage(Stage stage) {
		UI = new MainUI();
		stage.setTitle("Cinematrix");
        stage.getIcons().add(new Image(getClass().getResource("images/CinematrixIcon.png").toExternalForm()));
        stage.setScene(UI.getScene());
        stage.show();
	}
}
