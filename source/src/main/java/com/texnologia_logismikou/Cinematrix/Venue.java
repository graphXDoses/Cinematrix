package com.texnologia_logismikou.Cinematrix;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.VenueDocument;

public class Venue
{
	
	private VenueDocument doc;
	//private Cinema cinema = null;
	
	public Venue(VenueDocument doc)
	{
		
		this.doc = doc;
	}

	public VenueDocument getDoc() {
		return doc;
	}

	public void setDoc(VenueDocument doc) {
		this.doc = doc;
	}
}
