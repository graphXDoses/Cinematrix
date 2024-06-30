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
	
	//Empty constructor
	//MUST REMAIN!!!
	public Movie() {
		this.modal = new MovieModal("BLANK");
		this.modal.getController().setData(this);
	}

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

	public void setDirector(String director) {
		this.director = director;
	}
	
	//Creates a string that can be given to Cloud Storage to fetch the image of the movie.
	//It assumes that all the images in the database are name like so: "my_movie_name.png"
	public String titleToDownloadable() {
		
		String downloadable = "";
		String titleSplit[];
		
		titleSplit = this.title.toLowerCase().split("\\s+");
		for(int i = 0; i < titleSplit.length; i++) {
			downloadable += titleSplit[i];
			if(i != titleSplit.length-1) {
				downloadable += "_";
			}
		}
		
		return downloadable;
	}
}
