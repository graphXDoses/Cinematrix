package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

import java.util.ArrayList;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.Cinema;

public class MovieFields extends Fields {

	/*
	 * 	If you add a new field remember to update the QueryParameter.
	 */
	
	private StringField uid;
	private StringField title;
	private DoubleField releaseYear;
	private StringField mpRating;
	private DoubleField duration;
	private StringField ytTrailerUrl;
	private ArrayField<StringField> categories;
	private ArrayField<StringField> cinemas;
	private StringField description;
	private StringField director;
	
	public MovieFields(String title, int releaseYear, String mpRating, int duration,
			String ytTrailerUrl, String[] categories, Cinema[] cinemas,
			String description, String director) {
		
		this.uid = new StringField(title + "_" + System.currentTimeMillis());
		this.title = new StringField(title);
		this.releaseYear = new DoubleField(releaseYear);
		this.mpRating = new StringField(mpRating);
		this.duration = new DoubleField(duration);
		this.ytTrailerUrl = new StringField(ytTrailerUrl);
		
		this.categories = new ArrayField<StringField>(StringField.toStringFieldArray(categories));
		
		List<String> cinemaUids = new ArrayList<String>();
		for(Cinema i: cinemas) {
			cinemaUids.add(i.getDoc().getFields().getUid().getStringValue());
		}
		this.cinemas = new ArrayField<StringField>(StringField.toStringFieldArray((String[]) cinemaUids.toArray()));
		this.description = new StringField(description);
		this.director = new StringField(director);
	}
	
	public StringField getTitle() {
		return title;
	}
	public void setTitle(StringField title) {
		this.title = title;
	}
	public DoubleField getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(DoubleField releaseYear) {
		this.releaseYear = releaseYear;
	}
	public StringField getMpRating() {
		return mpRating;
	}
	public void setMpRating(StringField mpRating) {
		this.mpRating = mpRating;
	}
	public DoubleField getDuration() {
		return duration;
	}
	public void setDuration(DoubleField duration) {
		this.duration = duration;
	}
	public StringField getYtTrailerUrl() {
		return ytTrailerUrl;
	}
	public void setYtTrailerUrl(StringField ytTrailerUrl) {
		this.ytTrailerUrl = ytTrailerUrl;
	}
	public ArrayField<StringField> getCategories() {
		return categories;
	}
	public void setCategories(ArrayField<StringField> categories) {
		this.categories = categories;
	}
	public ArrayField<StringField> getCinemas() {
		return cinemas;
	}
	public void setCinemas(ArrayField<StringField> cinemas) {
		this.cinemas = cinemas;
	}
	public StringField getDescription() {
		return description;
	}
	public void setDescription(StringField description) {
		this.description = description;
	}
	public StringField getDirector() {
		return director;
	}
	public void setDirector(StringField director) {
		this.director = director;
	}
	public StringField getUid() {
		return uid;
	}
	public void setUid(StringField uid) {
		this.uid = uid;
	}
}
