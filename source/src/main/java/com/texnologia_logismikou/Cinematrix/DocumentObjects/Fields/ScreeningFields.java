package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class ScreeningFields extends Fields {

	private StringField movie;
	private DoubleField time;
	private DoubleField date;
	
	public ScreeningFields(String movie, Double time, Double date) {
		
		this.movie = new StringField(movie);
		this.time = new DoubleField(time);
		this.date = new DoubleField(date);
	}
	
	public StringField getMovie() {
		return movie;
	}
	public void setMovie(StringField movie) {
		this.movie = movie;
	}
	public DoubleField getTime() {
		return time;
	}
	public void setTime(DoubleField time) {
		this.time = time;
	}
	public DoubleField getDate() {
		return date;
	}
	public void setDate(DoubleField date) {
		this.date = date;
	}
}
