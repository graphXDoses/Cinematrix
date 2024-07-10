package com.texnologia_logismikou.Cinematrix.ResponseBodies;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.VenueDocument;

public class ListVenuesResponseBody {
	
	private ErrorResponseBody error;
	
	private String nextPageToken;
	private VenueDocument[] documents;
	
	public String getNextPageToken() {
		return nextPageToken;
	}
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
	public VenueDocument[] getDocuments() {
		return documents;
	}
	public void setDocuments(VenueDocument[] documents) {
		this.documents = documents;
	}
	public ErrorResponseBody getError() {
		return error;
	}
	public void setError(ErrorResponseBody error) {
		this.error = error;
	}
}
