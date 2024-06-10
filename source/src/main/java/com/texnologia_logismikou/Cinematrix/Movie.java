package com.texnologia_logismikou.Cinematrix;

import java.util.HashMap;

public class Movie {
	
	private String title;
	private int releaseDate;
	private int duration;
	private int expireDate;
	private String movieType;
	private String description;
	private String director;
	private String trailerLink;
	
	//Empty constructor
	//MUST REMAIN!!!
	public Movie() {
		
	}
	
	//Constructor with some fields initialized for testing.
	public Movie(String title, int releaseDate, int duration, int expireDate) {
		
		this.title = title;
		this.releaseDate = releaseDate;
		this.expireDate = expireDate;
		this.duration = duration;
	}
	
	//Getters and Setters
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(int releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(int expireDate) {
		this.expireDate = expireDate;
	}
	public String getMovieType() {
		return movieType;
	}
	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getTrailerLink() {
		return trailerLink;
	}
	public void setTrailerLink(String trailerLink) {
		this.trailerLink = trailerLink;
	}
}
