package com.texnologia_logismikou.Cinematrix;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class Cinema_System {
	
	private Firebase firebase = new Firebase();
	private FirestoreDB firestore = new FirestoreDB();
	
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
	
	public void setupFirebase() {
		firebase.initializeFirebase();
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
	
	public void handleInputUser() {
		
		Customer user = new Customer();
		
		System.out.println("A to add new User, S to show all Users: ");
		Scanner input = new Scanner(System.in);
		if(!input.nextLine().equals("A")) {
			
			return;
		}
		
		System.out.println("Give Name: ");
		user.setFull_name(input.nextLine());
		System.out.println("Give email: ");
		user.setEmail(input.nextLine());
		System.out.println("Give phone: ");
		user.setPhone_number(input.nextLine());
		System.out.println("Give password: ");
		user.setPassword(input.nextLine());
		
		//By default no user can be registered as admin on their own.
		user.setAdmin(false);
		
		addUsersToDb(user);
	}

	public void handleLoginUser() {
		
		String name;
		String password;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Give Name: ");
		name = input.nextLine();
		System.out.println("Give password: ");
		password = input.nextLine();

		userLogin(name, password);
	}	
	
	private void addMoviesToDb(Movie movie) {
		
		if(firebase.appExists()) {
			if(!firestore.dbExists()) {
				firestore.setDatabase();
			}
			firestore.addMovie(movie);
		}
	}

	public void updateAllMovieList() {

		if(!firebase.appExists()) {
			return;
		}
		
		if(!firestore.dbExists()) {
			firestore.setDatabase();
		}
		
		allMovieList = firestore.fetchAllMovies();
		System.out.println(allMovieList.toString());
	}
	
	private void addUsersToDb(Customer user) {
		
		if(!firebase.appExists()) {
			return;
		}
		
		if(!firestore.dbExists()) {
			firestore.setDatabase();
		}
		
		firestore.addUser(user);
	}
	
	private void userLogin(String name, String password) {
		
		Customer user = null;
		
		if(!firebase.appExists()) {
			return;
		}
		
		if(!firestore.dbExists()) {
			firestore.setDatabase();
		}
		
		user = firestore.fetchUser(name);
		if(user == null) {
			System.out.println("User doesn't exist!");
			return;
		}
		
		if(user.getPassword().equals(password)) {
			System.out.println("Log in successful!");
		} else {
			System.out.println("Incorrect password!");
		}
	}
}

