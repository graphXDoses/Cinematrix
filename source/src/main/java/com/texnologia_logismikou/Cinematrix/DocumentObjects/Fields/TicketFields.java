package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

import java.time.LocalDateTime;

public class TicketFields extends Fields {

	private StringField movieName;
	private DoubleField movieDuration;
	private StringField movieMpaRating;
	private StringField cinemaName;
	private StringField cinemaAddress;
	private StringField venueName;
	private DoubleField seatNum;
	private TimestampField date;
	private StringField uid;
	
	public TicketFields(String movieName, double movieDuration, String movieMpaRating
			, String cinemaName, String cinemaAddress, String venueName, int seatNum, LocalDateTime date) {
		
		this.movieName = new StringField(movieName);
		this.movieDuration = new DoubleField(movieDuration);
		this.movieMpaRating = new StringField(movieMpaRating);
		this.cinemaName = new StringField(cinemaName);
		this.cinemaAddress = new StringField(cinemaAddress);
		this.venueName = new StringField(venueName);
		this.seatNum = new DoubleField(seatNum);
		
		String dateString = date.getYear() + "-" + date.getMonth() + "-" + date.getDayOfMonth() 
							+ "T" + date.getHour() + ":" + date.getMinute() + ":00Z";
		this.date = new TimestampField(dateString);
		this.uid = new StringField("ticket_" + System.currentTimeMillis());
	}
	
	public StringField getUid() {
		return uid;
	}

	public void setUid(StringField uid) {
		this.uid = uid;
	}

	public StringField getMovieName() {
		return movieName;
	}
	public void setMovieName(StringField movieName) {
		this.movieName = movieName;
	}
	public DoubleField getMovieDuration() {
		return movieDuration;
	}
	public void setMovieDuration(DoubleField movieDuration) {
		this.movieDuration = movieDuration;
	}
	public StringField getMovieMpaRating() {
		return movieMpaRating;
	}
	public void setMovieMpaRating(StringField movieMpaRating) {
		this.movieMpaRating = movieMpaRating;
	}
	public StringField getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(StringField cinemaName) {
		this.cinemaName = cinemaName;
	}
	public StringField getCinemaAddress() {
		return cinemaAddress;
	}
	public void setCinemaAddress(StringField cinemaAddress) {
		this.cinemaAddress = cinemaAddress;
	}
	public StringField getVenueName() {
		return venueName;
	}
	public void setVenueName(StringField venueName) {
		this.venueName = venueName;
	}
	public DoubleField getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(DoubleField seatNum) {
		this.seatNum = seatNum;
	}

	public TimestampField getDate() {
		return date;
	}

	public void setDate(TimestampField date) {
		this.date = date;
	}
}
