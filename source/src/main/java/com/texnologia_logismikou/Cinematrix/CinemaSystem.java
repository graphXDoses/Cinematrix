package com.texnologia_logismikou.Cinematrix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.*;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.*;
import com.texnologia_logismikou.Cinematrix.ResponseBodies.*;
import com.texnologia_logismikou.Cinematrix.Managers.MainDisplay;
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

import java.nio.file.Path;
import com.texnologia_logismikou.Cinematrix.Views.LoginView;

public class CinemaSystem {
	private static CinemaSystem instance = null;
	
	private final String webKey = "AIzaSyDTn8MSxkAuIX-sH-_I_vwAwVqIt77sORU";
	
	private static UserCore currentUser;
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
		
		contexts.add(new Context("Movies", "images/movie.png", new AllMoviesView(), new MovieDetailsView(), new SeatSelectionView()));
		contexts.add(new Context("Cinemas", "images/theater.png", new NearCinemasView()));
		contexts.add(new Context("Account", "images/account.png", new LoginView(), new SignUpView(), new UserDashboardView()));
		
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

	public UserCore getCurrentUser() { return(currentUser); }

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
		
		return signInResponse.getIdToken();
	}
	
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
	
	public MovieDocument getMovieDocument(String name) {
		
		MovieDocument response = new MovieDocument();
		
		try {
			response = RequestHandler.getInstance().getMovieDocumentRequest(name);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		if(response.getError() != null) {
			System.out.println("Error retrieving movie doc. Error details: " + response.getError().getMessage());
			return null;
		} 
		
		return response;
	}

	public void updateMovieDocument(MovieFields fields, String firebaseId) {
		
		MovieDocument updateResponse = new MovieDocument();
		
		try {
			updateResponse = RequestHandler.getInstance().updateMovieDocumentRequest(fields, firebaseId);
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
		
		if(updateResponse.getError() != null) {
			System.out.println(updateResponse.getError().getMessage());
		} else {
			System.out.println("Movie Document succesfully updated at: " + updateResponse.getUpdateTime());
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
	
	public void createCinemaDocument(String firebaseId, int numOfRooms, CinemaFields fields) {
		
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
		
		for(int i = 0; i < numOfRooms; i++) {
			
			RoomDocument createRoomResponse = new RoomDocument();
			String roomName = "Room" + (i+1);
			try {
				RequestHandler.getInstance().createRoomDocumentRequest(firebaseId, roomName, fields.getName().getStringValue());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(createRoomResponse.getError() != null) {
				System.out.println("Error creating room document. Error details: " + createRoomResponse.getError().getMessage());
				return;
			}
		}
	}
	
	public void fetchAllCinemas() {
		
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
			
			cinemas.add(new Cinema(cinema));
		}
	}
	
	public void fetchAllMovies() {
		
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
		
		StorageHandler storage = StorageHandler.getInstance();
		
		for(MovieDocument movie: moviesList.getDocuments()) {
						
			String imageName = "";
			
			try {
				imageName = storage.downloadMovieImage(movie.getFields().getTitle().getStringValue());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			movies.add(new Movie(movie, imageName));
		}
	}
}
