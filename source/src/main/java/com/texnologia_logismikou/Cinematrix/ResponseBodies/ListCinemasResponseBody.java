package com.texnologia_logismikou.Cinematrix.ResponseBodies;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.CinemaDocument;

public class ListCinemasResponseBody {

	private ErrorResponseBody error;
	
	private String nextPageToken;
	private CinemaDocument[] documents;
	
	public String getNextPageToken() {
		return nextPageToken;
	}
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
	public CinemaDocument[] getDocuments() {
		return documents;
	}
	public void setDocuments(CinemaDocument[] documents) {
		this.documents = documents;
	}
	
	public ErrorResponseBody getError() {
		return error;
	}
	public void setError(ErrorResponseBody error) {
		this.error = error;
	}
}
