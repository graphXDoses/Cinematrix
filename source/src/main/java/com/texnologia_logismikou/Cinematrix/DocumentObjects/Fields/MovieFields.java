package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class MovieFields extends Fields {
	
	private StringField uid;
	private StringField title;
	private DoubleField releaseYear;
	private StringField mpaRating;
	private DoubleField duration;
	private StringField ytTrailerUrl;
	private ArrayField<StringField> categories;
	private StringField description;
	private StringField director;
	private StringField fullName;
	
	public MovieFields(String title, int releaseYear, String mpaRating, int duration,
			String ytTrailerUrl, String[] categories,
			String description, String director) {
		
		this.title = new StringField(title);
		title = StringField.toPascalCase(title);
		String uid = title.toLowerCase() + "_" + System.currentTimeMillis();
		this.uid = new StringField(uid);
		this.releaseYear = new DoubleField(releaseYear);
		this.mpaRating = new StringField(mpaRating);
		this.duration = new DoubleField(duration);
		this.ytTrailerUrl = new StringField(ytTrailerUrl);
		this.categories = new ArrayField<StringField>(StringField.toStringFieldArray(categories));
		this.description = new StringField(description);
		this.director = new StringField(director);
		
		this.fullName = new StringField(this.title + ", " + this.releaseYear);
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
	public StringField getMpaRating() {
		return mpaRating;
	}
	public void setMpRating(StringField mpRating) {
		this.mpaRating = mpRating;
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

	public StringField getFullName() {
		return fullName;
	}

	public void setFullName(StringField fullName) {
		this.fullName = fullName;
	}
}
