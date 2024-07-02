package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class MovieFields {

	private String name;
	private ArrayField<StringField> cinemas;
	private double duration;
	
	public MovieFields(String name, StringField[] cinemas, double duration) {
		
		this.name = name;
		this.duration = duration;
		ArrayField<StringField> cinemasField = new ArrayField<StringField>(cinemas);
		this.cinemas = cinemasField;
	}
	public ArrayField<StringField> getCinemas() {
		return cinemas;
	}
	public void setCinemas(ArrayField<StringField> cinemas) {
		this.cinemas = cinemas;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
}
