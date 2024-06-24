package com.texnologia_logismikou.Cinematrix;

import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;

public class Movie {
	
	private String title;
	private int    year;
	private String ytTrailerURL;
	private final MovieModal modal;
	
	public Movie(String title, String cover_img_path, int year, String ytTrailerURL)
	{
		this.title = title;
		this.year = year;
		this.ytTrailerURL = ytTrailerURL;
		
		this.modal = new MovieModal(cover_img_path);
		this.modal.getController().setData(this);
	}

	public String getTitle() { return(title); }
	public int    getYear() { return(year); }
	public String getFullName() { return( getTitle() + " (" + getYear() + ")"); }
	public MovieModal getModal() { return(modal); }

	public String getYtTrailerURL() {
		return ytTrailerURL;
	}
}
