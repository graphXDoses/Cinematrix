package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class MovieFields {

	private StringField name;
	private ArrayField<StringField> cinemas;
	private DoubleField duration;
	
	public MovieFields(String name, StringField[] cinemas, double duration) {
		
		StringField stringField = new StringField(name);
		this.name = stringField;
		
		ArrayField<StringField> cinemasField = new ArrayField<StringField>(cinemas);
		this.cinemas = cinemasField;
		
		DoubleField doubleField = new DoubleField(duration);
		this.duration = doubleField;
	}
	public ArrayField<StringField> getCinemas() {
		return cinemas;
	}
	public void setCinemas(ArrayField<StringField> cinemas) {
		this.cinemas = cinemas;
	}
	public DoubleField getDuration() {
		return duration;
	}
	public void setDuration(DoubleField duration) {
		this.duration = duration;
	}
	public StringField getName() {
		return name;
	}
	public void setName(StringField name) {
		this.name = name;
	}
}
