package com.texnologia_logismikou.Cinematrix;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Screening
{
	private Movie movie;
	private Cinema cinema;
	private Venue venue;
	private LocalDateTime date;
	
	public Screening(Movie movie, Cinema cinema, Venue venue, LocalDateTime date)
	{
		this.movie = movie;
		this.cinema = cinema;
		this.venue = venue;
		this.date = date;
		
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
