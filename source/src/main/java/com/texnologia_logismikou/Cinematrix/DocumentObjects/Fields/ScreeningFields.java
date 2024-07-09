package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class ScreeningFields extends Fields {

	/*
	 * 	If you add a new field remember to update the QueryParameter.
	 */
	
	private StringField uid;
	private StringField movieUid;
	private StringField cinemaUid;
	private StringField venueUid;
	private StringField time;
	
	public ScreeningFields(String movieUid, String cinemaUid, String venueUid, String time) {
		
		this.uid = new StringField("screening_" + System.currentTimeMillis());
		this.movieUid = new StringField(movieUid);
		this.cinemaUid = new StringField(cinemaUid);
		this.venueUid = new StringField(venueUid);
		this.time = new StringField(time);
	}
	
	public StringField getUid() {
		return uid;
	}
	public void setUid(StringField uid) {
		this.uid = uid;
	}
	public StringField getMovieUid() {
		return movieUid;
	}
	public void setMovieUid(StringField movieUid) {
		this.movieUid = movieUid;
	}
	public StringField getCinemaUid() {
		return cinemaUid;
	}
	public void setCinemaUid(StringField cinemaUid) {
		this.cinemaUid = cinemaUid;
	}
	public StringField getVenueUid() {
		return venueUid;
	}
	public void setVenueUid(StringField venueUid) {
		this.venueUid = venueUid;
	}
	public StringField getTime() {
		return time;
	}
	public void setTime(StringField time) {
		this.time = time;
	}
}
