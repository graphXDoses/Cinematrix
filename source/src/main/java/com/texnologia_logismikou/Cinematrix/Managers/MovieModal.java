package com.texnologia_logismikou.Cinematrix.Managers;

import java.io.File;
import java.nio.file.Path;

import com.texnologia_logismikou.Cinematrix.App;
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

	public void setUrl(String movieName) throws NullPointerException {
		if(movieName == null)
			throw new NullPointerException();
		this.url = "/_" + movieName + "_Cover.jpg";
		//getController().setData(this);
	}

	public Image getCoverImage() { return(cover_img); }
	
	
}
