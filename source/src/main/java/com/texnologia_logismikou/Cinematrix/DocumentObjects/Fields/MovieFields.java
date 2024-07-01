package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class MovieFields {

	private NameField name;
	private CinemaArrayField cinemas;
	private DoubleField duration;
	
	public MovieFields(String name, NameField[] cinemas, double duration) {
		
		NameField nameField = new NameField(name);
		this.name = nameField;
		
		CinemaArrayField cinemasField = new CinemaArrayField(cinemas);
		this.cinemas = cinemasField;
		
		DoubleField doubleField = new DoubleField(duration);
		this.duration = doubleField;
	}
	
	public NameField getName() {
		return name;
	}
	public void setName(NameField name) {
		this.name = name;
	}
	public CinemaArrayField getCinemas() {
		return cinemas;
	}
	public void setCinemas(CinemaArrayField cinemas) {
		this.cinemas = cinemas;
	}
	public DoubleField getDuration() {
		return duration;
	}
	public void setDuration(DoubleField duration) {
		this.duration = duration;
	}
}
