package com.texnologia_logismikou.Cinematrix;

import java.util.ArrayList;
import java.util.List;

public class Screening
{
	private Movie movie;
	private Cinema cinema;
	private Venue venue;
	private List<String> hours = new ArrayList<>();
	
	public Screening(Movie movie, Cinema cinema, Venue venue, List<String> hours)
	{
		this.movie = movie;
		this.cinema = cinema;
		this.venue = venue;
		this.hours = hours;
		
		this.movie.associateScreening(this);
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public List<String> getHours() {
		return hours;
	}

	public void setHours(List<String> hours) {
		this.hours = hours;
	}
}
