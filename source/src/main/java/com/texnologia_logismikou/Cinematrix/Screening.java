package com.texnologia_logismikou.Cinematrix;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Screening
{
	private Movie movie;
	private Cinema cinema;
	private Venue venue;
	private List<LocalDateTime> hours = new ArrayList<>();
	
	public Screening(Movie movie, Cinema cinema, Venue venue, List<LocalDateTime> hours)
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

	public List<LocalDateTime> getHours() {
		return hours;
	}

	public void setHours(List<LocalDateTime> hours) {
		this.hours = hours;
	}
}
