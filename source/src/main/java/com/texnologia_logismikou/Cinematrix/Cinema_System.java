package com.texnologia_logismikou.Cinematrix;

import java.util.ArrayList;

public class Cinema_System {
	
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
}
