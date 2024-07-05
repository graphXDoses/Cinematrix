package com.texnologia_logismikou.Cinematrix.ResponseBodies;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.MovieDocument;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.ArrayValuesField;

public class ListMoviesResponse {

	private String nextPageToken;
	private ArrayValuesField<MovieDocument> documents;
	
	public String getNextPageToken() {
		return nextPageToken;
	}
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
	public ArrayValuesField<MovieDocument> getDocuments() {
		return documents;
	}
	public void setDocuments(ArrayValuesField<MovieDocument> documents) {
		this.documents = documents;
	}
}
