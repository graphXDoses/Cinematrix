package com.texnologia_logismikou.Cinematrix;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
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
	public void initializeFirebaseApp() {
		
		try(InputStream serviceAccount = new FileInputStream(CREDENTIALS_PATH)) {
			
			try {
				FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
				
				try {
					app = FirebaseApp.initializeApp(options, "Cinematrix");
				} catch (Exception e) {
					
					System.out.println("A Firebase app already exists!");
					System.out.println("Exception details: " + e.toString());
					System.out.println("\n------------------------------\n");
					return;
				}
			} catch (IOException e) {
				
				System.out.println("There was an error verifying the credentials with Google. The process will be terminated.");
				System.out.println("Exception details: " + e.toString());
				System.out.println("\n------------------------------\n");
				return;
			}
			
			//Close file.
			serviceAccount.close();
		} catch(IOException e) {
			
			System.out.println("There was an error reading/closing the JSON file. The process will be terminated.");
			System.out.println("Exception details: " + e.toString());
			System.out.println("\n------------------------------\n");
			return;
		}
		
		System.out.println(FirebaseApp.getApps().toString());
	}
	
	//Deletes the Firebase App.
	public void deleteFirebaseApp() {
		
		app.delete();
		app = null;
	}
	
	//Method that gets a Firestore isntance and stores it in the field db.
	//Open the database connection but DO NOT FORGET to close it, using closeFirestore(), once the operation is done.
	public void getFirestore() {
		
		db = com.google.firebase.cloud.FirestoreClient.getFirestore();
	}
	
	//Closes the connection to the Firestore database.
	public void closeFirestore() {
		
		try {
			db.close();
		} catch (Exception e) {
			
			System.out.println("There was an error closing the connection with the Database.");
			System.out.println("Exception details: " + e.toString());
			System.out.println("\n------------------------------\n");
		}
		
		db = null;
	}
}
