package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.App;
import com.texnologia_logismikou.Cinematrix.Controllers.MovieModalController;

import javafx.scene.image.Image;

public class MovieModal extends Manager<MovieModalController> {

	private String url = null;
	private Image cover_img = null;
	
	public MovieModal(String url)
	{
		super.loadFXML("MovieModal");
		try {			
			this.setUrl(url);
			cover_img = new Image(App.class.getResource(getUrl()).toExternalForm());
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) throws NullPointerException {
		if(url == null)
			throw new NullPointerException();
		this.url = "images/_" + url + "_Cover.jpg";
		//getController().setData(this);
	}

	public Image getCoverImage() { return(cover_img); }
	
	
}
