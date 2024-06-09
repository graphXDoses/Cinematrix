package com.texnologia_logismikou.Cinematrix;

public class Movie {

	private String url = null;
	
	public Movie(String url)
	{
		this.setUrl(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
