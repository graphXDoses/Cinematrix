package com.texnologia_logismikou.Cinematrix;

import java.util.ArrayList;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.MovieDocument;
import com.texnologia_logismikou.Cinematrix.Managers.MovieModal;

public class Movie {
	
	private MovieDocument doc;
	private final MovieModal modal;
	
	private ArrayList<Screening> screenings = new ArrayList<Screening>();

	public Movie() {
		this.modal = new MovieModal("BLANK");
		this.modal.getController().setData(this);
	}

	public Movie(MovieDocument doc, String movieName) {
		
		this.doc = doc;
		
		this.modal = new MovieModal(movieName);
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

	public void associateScreening(Screening screening) {
		if(!this.screenings.contains(screening))
			this.screenings.add(screening);
	}
	
	public ArrayList<Screening> getAssociateScreenings()
	{
		return(this.screenings);
	}
}
