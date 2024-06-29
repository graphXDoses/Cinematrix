package com.texnologia_logismikou.Cinematrix.Responses;

public class SignInResponse {

	// Base class for the response after a Sign-In request.
	// Check com.texnologia_logismikou.Cinematrix.Requests.SignInRequest for the request body.
	
	private Error error;
	private String kind;
	private String localid;
	private String email;
	private String displayName;
	private String idToken;
	private String registered;
	private String refreshToken;
	private String epxiresIn;
	
	public Error getError() {
		return error;
	}
	public void setError(Error error) {
		this.error = error;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getLocalid() {
		return localid;
	}
	public void setLocalid(String localid) {
		this.localid = localid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getIdToken() {
		return idToken;
	}
	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	public String getRegistered() {
		return registered;
	}
	public void setRegistered(String registered) {
		this.registered = registered;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getEpxiresIn() {
		return epxiresIn;
	}
	public void setEpxiresIn(String epxiresIn) {
		this.epxiresIn = epxiresIn;
	}
}
