package com.texnologia_logismikou.Cinematrix.Managers;

import java.nio.file.Path;
import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Controllers.MovieModalController;

import javafx.scene.image.Image;

public class MovieModal extends Manager<MovieModalController> {

	private String url = null;
	private Image cover_img = null;
	
	public MovieModal(String movieName)
	{
		super.loadFXML("MovieModal");
		try {			
			this.setUrl(movieName);
			
			Path path = Path.of(CinematrixAPI.imagesPath + url);
			cover_img = new Image(path.toUri().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String movieName){
		if(movieName == null)
			this.url = "/_BLANK_Cover.jpg";
		else
			this.url = "/_" + movieName + "_Cover.jpg";
	}

	public Image getCoverImage() { return(cover_img); }
	
	
}
