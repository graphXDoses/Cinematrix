package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class MovieFields {

	private NameField name;
	private CinemaArrayField cinemas;
	private DoubleField duration;
	
	public MovieFields(NameField name, CinemaArrayField cinemas, DoubleField duration) {
		this.name = name;
		this.cinemas = cinemas;
		this.duration = duration;
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
