package com.texnologia_logismikou.Cinematrix;

import java.util.ArrayList;

import java.util.ArrayList;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.VenueDocument;

public class Venue
{
	
	private VenueDocument doc;
	private VenueTopdownType topdown_t;
	private VenueSoundSystem system_s;
//	private List<VenueTraits> traits;
	private VenueTraits traits;
	private ArrayList<Integer> reservedSeats = new ArrayList<Integer>();
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

	public VenueTopdownType getTopdown_t() {
		return topdown_t;
	}

	public void setTopdown_t(VenueTopdownType topdown_t) {
		this.topdown_t = topdown_t;
	}

	public VenueSoundSystem getSystem_s() {
		return system_s;
	}

	public void setSystem_s(VenueSoundSystem system_s) {
		this.system_s = system_s;
	}

	public VenueTraits getTraits() {
		return traits;
	}

	public void setTraits(VenueTraits traits) {
		this.traits = traits;
	}

	public ArrayList<Integer> getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(ArrayList<Integer> reservedSeats) {
		this.reservedSeats = reservedSeats;
	}
}
