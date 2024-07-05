package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class ScreeningFields extends Fields {

	/*
	 * 	If you add a new field remember to update the QueryParameter.
	 */
	
	private StringField movie;
	private TimestampField timestamp;
	private DoubleField reservedSeats;
	
	public ScreeningFields(String movie, String timestamp) {
		
		this.movie = new StringField(movie);
		this.timestamp = new TimestampField(timestamp);
	}
	
	public StringField getMovie() {
		return movie;
	}
	public void setMovie(StringField movie) {
		this.movie = movie;
	}
	public TimestampField getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(TimestampField timestamp) {
		this.timestamp = timestamp;
	}

	public DoubleField getAvailableSeats() {
		return reservedSeats;
	}

	public void setAvailableSeats(DoubleField availableSeats) {
		this.reservedSeats = availableSeats;
	}
}
