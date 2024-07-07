package com.texnologia_logismikou.Cinematrix;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.MovieDocument;
import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;

public class Movie {
	
	private MovieDocument doc;
	private final MovieModal modal;
	
	public Movie(MovieDocument doc, String imagePath) {
		
		this.doc = doc;
		
		this.modal = new MovieModal(imagePath);
		this.modal.getController().setData(this);
	}
	
	public String getDuration()
	{
		String result = "";
		int duration = (int) doc.getFields().getDuration().getDoubleValue();
		
		result += duration / 60 > 0 ? duration / 60 + " hr " + duration % 60 + " min"
				: duration + " min";
		
		return(result);
	}

	public MovieModal getModal() {
		return modal;
	}

	public MovieDocument getDoc() {
		return doc;
	}

	public void setDoc(MovieDocument doc) {
		this.doc = doc;
	}
}
