package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.Controllers.MovieModalController;

public class Movie extends Manager<MovieModalController> {

	private String url = null;
	
	public Movie(String url)
	{
		super.loadFXML("MovieModal");
		this.setUrl(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		getController().setData(this);
	}
}
