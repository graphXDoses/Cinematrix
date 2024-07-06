package com.texnologia_logismikou.Cinematrix.ResponseBodies;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.RoomDocument;

public class ListRoomsResponseBody {
	
	private ErrorResponseBody error;
	
	private String nextPageToken;
	private RoomDocument[] documents;
	
	public String getNextPageToken() {
		return nextPageToken;
	}
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
	public RoomDocument[] getDocuments() {
		return documents;
	}
	public void setDocuments(RoomDocument[] documents) {
		this.documents = documents;
	}
	public ErrorResponseBody getError() {
		return error;
	}
	public void setError(ErrorResponseBody error) {
		this.error = error;
	}
}
