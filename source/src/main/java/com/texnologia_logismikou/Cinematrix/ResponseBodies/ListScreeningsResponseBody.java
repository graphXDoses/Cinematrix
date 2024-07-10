package com.texnologia_logismikou.Cinematrix.ResponseBodies;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.ScreeningDocument;

public class ListScreeningsResponseBody {

	private ErrorResponseBody error;
	
	private String nextPageToken;
	private ScreeningDocument[] documents;
	
	public ErrorResponseBody getError() {
		return error;
	}
	public void setError(ErrorResponseBody error) {
		this.error = error;
	}
	public String getNextPageToken() {
		return nextPageToken;
	}
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
	public ScreeningDocument[] getDocuments() {
		return documents;
	}
	public void setDocuments(ScreeningDocument[] documents) {
		this.documents = documents;
	}
}
