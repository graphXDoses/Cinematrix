package com.texnologia_logismikou.Cinematrix;

public class Venue
{
	private VenueTopdownType topdown_t;
	private VenueSoundSystem system_s;
	private VenueTraits traits;
	private Cinema cinema = null;
	
	public Venue(VenueTopdownType topdown_t, VenueSoundSystem system_s, VenueTraits traits)
	{
		this.topdown_t = topdown_t;
		this.system_s = system_s;
		this.traits = traits;
	}
	
	public void setBelongingCinema(Cinema cinema)
	{
		this.cinema = cinema;
	}

	public VenueTopdownType getTopdown() {
		return topdown_t;
	}

	public VenueSoundSystem getSystem() {
		return system_s;
	}

	public VenueTraits getTraits() {
		return traits;
	}
}
