package com.texnologia_logismikou.Cinematrix;

import java.util.ArrayList;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.CinemaDocument;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.VenueDocument;
import com.texnologia_logismikou.Cinematrix.Managers.CinemaModal;

public class Cinema {
	
	private CinemaDocument doc;
	private ArrayList<VenueDocument> venues;
	private CinemaModal modal;
	//private List<Movie> screening = new ArrayList<>();
	
	public Cinema(CinemaDocument document)
	{	
		this.doc = document;
		
		this.modal = new CinemaModal();
		this.modal.getController().setData(this);
	}

	public CinemaModal getModal() {
		return modal;
	}

	public CinemaDocument getDoc() {
		return doc;
	}

	public void setDoc(CinemaDocument doc) {
		this.doc = doc;
	}
	public ArrayList<VenueDocument> getVenues() {
		return venues;
	}

	public void setVenues(ArrayList<VenueDocument> venues) {
		this.venues = venues;
	}
}
