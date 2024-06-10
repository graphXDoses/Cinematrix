package com.texnologia_logismikou.Cinematrix;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class Cinema_System {
	
	private static final String CREDENTIALS_PATH = "C:/Users/petsi/University/TexLog/firebase_cred_phoebus.json"; 
	
	private static FirebaseApp app = null;
	private static Firestore db = null;
	
	private ArrayList<Movie> allMovieList = new ArrayList<>();
	private ArrayList<Movie> nowMovieList = new ArrayList<>();
	private ArrayList<Movie> futureMovieList = new ArrayList<>();
	private ArrayList<Room> RoomList = new ArrayList<>();
	private ArrayList<Screening> ScreeningList = new ArrayList<>();
	private ArrayList<Customer> CustomerList = new ArrayList<>();
	private ArrayList<Ticket> TicketList = new ArrayList<>();
	
	public void addMovie(ArrayList<Movie> movie_list, Movie movie) {
		movie_list.add(movie);
	}
	
	public void removeMovie(ArrayList<Movie> movie_list, Movie movie) {
		movie_list.remove(movie);
	}
	
	public void checkMovie(Movie movie, int date) {
		if (date <= movie.getReleaseDate()) {
			addMovie(futureMovieList, movie);
		}
		if ((movie.getReleaseDate() <= date) && (date <= movie.getExpireDate())) {
			removeMovie(futureMovieList, movie);
			addMovie(nowMovieList, movie);
		}
		if (movie.getExpireDate() <= date) {
			removeMovie(nowMovieList, movie);
			
		}
	}
	
	public void addCustomer(ArrayList<Customer> customer_list, Customer customer) {
		customer_list.add(customer);
	}
	
	public void removeCustomer(ArrayList<Customer> customer_list, Customer customer) {
		customer_list.remove(customer);
	}
	
	//Getters and Setters
	public ArrayList<Movie> getAllMovieList() {
		return allMovieList;
	}

	public void setAllMovieList(ArrayList<Movie> allMovieList) {
		this.allMovieList = allMovieList;
	}

	public ArrayList<Movie> getNowMovieList() {
		return nowMovieList;
	}

	public void setNowMovieList(ArrayList<Movie> nowMovieList) {
		this.nowMovieList = nowMovieList;
	}

	public ArrayList<Movie> getFutureMovieList() {
		return futureMovieList;
	}

	public void setFutureMovieList(ArrayList<Movie> futureMovieList) {
		this.futureMovieList = futureMovieList;
	}

	public ArrayList<Room> getRoomList() {
		return RoomList;
	}

	public void setRoomList(ArrayList<Room> roomList) {
		RoomList = roomList;
	}

	public ArrayList<Screening> getScreeningList() {
		return ScreeningList;
	}

	public void setScreeningList(ArrayList<Screening> screeningList) {
		ScreeningList = screeningList;
	}

	public ArrayList<Customer> getCustomerList() {
		return CustomerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		CustomerList = customerList;
	}

	public ArrayList<Ticket> getTicketList() {
		return TicketList;
	}

	public void setTicketList(ArrayList<Ticket> ticketList) {
		TicketList = ticketList;
	}
	
	//Initializes the FirebaseApplication.
	//Exceptions will be caught and messages will be printed corresponding to the place of the error.
	//There is a chance that the FirebaseApp gets initialized but the JSON file doesn't close properly.
	//This method also sets a Firestore instance.
	public void initializeFirebase() {
		
		try(InputStream serviceAccount = new FileInputStream(CREDENTIALS_PATH)) {
			
			try {
				FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
				
				try {
					app = FirebaseApp.initializeApp(options);
				} catch (Exception e) {
					
					System.out.println("A Firebase app already exists!");
					Cinema_System.showExceptionDetails(e);
					return;
				}
			} catch (IOException e) {
				
				System.out.println("There was an error verifying the credentials with Google. The process will be terminated.");
				Cinema_System.showExceptionDetails(e);
				return;
			}
			
			//Close file.
			serviceAccount.close();
		} catch(IOException e) {
			
			System.out.println("There was an error reading/closing the JSON file. The process will be terminated.");
			Cinema_System.showExceptionDetails(e);
			return;
		}
		
		db = com.google.firebase.cloud.FirestoreClient.getFirestore();
		
		System.out.println(FirebaseApp.getApps().toString());
	}
	
	//Sets the field db to an instance of Firestore.
	public void setFirestore() {
		
		db = com.google.firebase.cloud.FirestoreClient.getFirestore();
	}
	
	//Deletes the Firebase App.
	public void deleteFirebaseApp() {
		
		app.delete();
		app = null;
	}
	
	//Closes the connection to the Firestore database.
	public void closeFirestore() {
		
		try {
			db.close();
		} catch (Exception e) {
			
			System.out.println("There was an error closing the connection with the Database.");
			Cinema_System.showExceptionDetails(e);
		}
		
		db = null;
	}
	
	//Receives input for some of a movies fields.
	//Then calls addMovieToDb while the movie instance generated a HashMap<String, Object>.
	public void handleInput()  {
		
		Movie movie = new Movie();
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Type movie name: ");
		movie.setTitle(input.nextLine()); 
		
		System.out.println("\nType movie duration: ");
		movie.setDuration(input.nextInt());
		
		System.out.println("\nType movie release date: ");
		movie.setReleaseDate(input.nextInt());
		
		System.out.println("\nType movie expire date: ");
		movie.setExpireDate(input.nextInt());
		
		addMoviesToDb(movie);
	}
	
	//This method creates a new document, with the name Movie.title and stores all the information of 
	//the HashMap inside it.
	private void addMoviesToDb(Movie movie) {
		
		if(db == null) {
			
			System.out.println("No Firestore instance. Use initializeFirebase().");
			return;
		}
		
		ApiFuture<WriteResult> future = db.collection("Movies").document(movie.getTitle()).set(movie, SetOptions.merge());
		
		try {
			
			System.out.println("Database updated at: " + future.get().getUpdateTime());
			closeFirestore();
		} catch (CancellationException e) {
			
			System.out.println("The process was cancelled.");
			Cinema_System.showExceptionDetails(e);
		} catch(ExecutionException e) {
			
			System.out.println("The execution run into an error.");
			Cinema_System.showExceptionDetails(e);
		} catch(InterruptedException e) {
			
			System.out.println("The process was interrupted.");
			Cinema_System.showExceptionDetails(e);
		}
	}
	
	//A method used to print information in the console when exceptions are caught.
	public static void showExceptionDetails(Exception e) {
		
		System.out.println("Exception details: " + e.toString());
		System.out.println("\n----------------------------------------------------------------------------------\n");
	}

	public void updateAllMovieList() {
		
		CollectionReference colRef = null;
		DocumentReference docRef = null;
		DocumentSnapshot docSnap = null;
		
		if(db == null) {
			
			System.out.println("No instance of Firestore!");
			return;
		}
		
		colRef = db.collection("Movies");
		
		return;
	}
}
