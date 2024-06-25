package com.texnologia_logismikou.Cinematrix;

import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;

public class Movie {
	
	private String title;
	private int    year;
	private String MPArating;
	private int    duration;
	private String ytTrailerURL;
	private String description;
	private String director;
	private final MovieModal modal;
	
	public Movie(String title,
				 String cover_img_path,
				 int    year,
				 String MPArating,
				 int    duration,
				 String ytTrailerURL,
				 String description,
				 String director)
	{
		this.title = title;
		this.year = year;
		this.MPArating = MPArating;
		this.duration = duration;
		this.ytTrailerURL = ytTrailerURL;
		this.description = description;
		this.director = director;
		
		this.modal = new MovieModal(cover_img_path);
		this.modal.getController().setData(this);
	}

	public String getTitle() { return(title); }
	public int    getYear() { return(year); }
	public String getFullName() { return( getTitle() + " (" + getYear() + ")"); }
	public String getDuration()
	{
		String result = "";
		
		result += duration / 60 > 0 ? duration / 60 + " hr " + duration % 60 + " min"
				: duration + " min";
		
		return(result);
	}
	public MovieModal getModal() { return(modal); }

	public String getYtTrailerURL() {
		return ytTrailerURL;
	}

	public String getMPArating() {
		return MPArating;
	}

	public String getDescription() {
		return description;
	}

	public String getDirector() {
		return director;
	}
}
