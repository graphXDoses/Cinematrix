package com.texnologia_logismikou.Cinematrix.ResponseBodies;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.MovieDocument;

public class ListMoviesResponseBody {

	private String nextPageToken;
	private MovieDocument[] documents;
	
	public String getNextPageToken() {
		return nextPageToken;
	}
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
	public MovieDocument[] getDocuments() {
		return documents;
	}
	public void setDocuments(MovieDocument[] documents) {
		this.documents = documents;
	}
}
