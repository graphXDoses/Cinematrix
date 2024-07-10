package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

import java.time.LocalDateTime;

public class ScreeningFields extends Fields {

	/*
	 * 	If you add a new field remember to update the QueryParameter.
	 */
	
	private StringField uid;
	private StringField movieUid;
	private StringField cinemaUid;
	private StringField venueUid;
	private TimestampField date;
	
	public ScreeningFields(String movieUid, String cinemaUid, String venueUid, LocalDateTime date) {
		
		this.uid = new StringField("screening_" + System.currentTimeMillis());
		this.movieUid = new StringField(movieUid);
		this.cinemaUid = new StringField(cinemaUid);
		this.venueUid = new StringField(venueUid);
		
		String dateString = date.getYear() + "-" + date.getMonth().getValue() + "-" + date.getDayOfMonth() 
							+ "T" + date.getHour() + ":" + date.getMinute() + ":00Z";
		System.out.println(dateString);
		this.date = new TimestampField(dateString);
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

	public TimestampField getDate() {
		return date;
	}

	public void setDate(TimestampField date) {
		this.date = date;
	}
}
