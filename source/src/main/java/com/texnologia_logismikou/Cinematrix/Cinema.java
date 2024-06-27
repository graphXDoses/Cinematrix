package com.texnologia_logismikou.Cinematrix;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.Managers.CinemaModal;

public class Cinema {
	
	private String name;
	private String address;
	private float  distance;
	private CinemaModal modal;
	private List<Movie> screening = new ArrayList<>();
	
	public Cinema(String name, String address, float distance)
	{
		this.name = name;
		this.address = address;
		this.distance = distance;
		
		this.modal = new CinemaModal();
		this.modal.getController().setData(this);
	}
	
	public void setMovieScreening(Movie movie,
									String soundSystem,
									List<Date> dates,
									List<String> hours)
	{
		screening.add(movie);
		this.modal.getController().setScreeningData(movie, soundSystem, dates, hours);
	}
	
	public boolean isScreeningTheMovie(Movie movie)
	{
		return(screening.contains(movie));
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public float getDistance() {
		return distance;
	}

	public CinemaModal getModal() {
		return modal;
	}
}
