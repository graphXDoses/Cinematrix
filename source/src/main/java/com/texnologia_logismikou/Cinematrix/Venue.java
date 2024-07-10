package com.texnologia_logismikou.Cinematrix;

import java.util.List;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.VenueDocument;

public class Venue
{
	
	private VenueDocument doc;
	private VenueTopdownType topdown_t;
	private VenueSoundSystem system_s;
//	private List<VenueTraits> traits;
	private VenueTraits traits;
	//private Cinema cinema = null;
	
	public Venue(VenueDocument doc)
	{
		
		this.doc = doc;
	}

	public Venue(VenueTopdownType typeA,
				VenueSoundSystem standardSystem,
				VenueTraits reservedSeating) {
		
		this.topdown_t = topdown_t;
        this.system_s = system_s;
        this.traits = traits;
	}

	public VenueDocument getDoc() {
		return doc;
	}

	public void setDoc(VenueDocument doc) {
		this.doc = doc;
	}
}
