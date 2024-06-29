package com.texnologia_logismikou.Cinematrix;

import java.util.ArrayList;

public class SignInRequest {

	//For request
	private boolean returnSecureToken;
	private String email;
	private String password;

	//For response
	private Error error;
	
	public SignInRequest(TemporaryUser user) {
		
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.returnSecureToken = true;
	}

	public boolean isReturnSecureToken() {
		return returnSecureToken;
	}

	public void setReturnSecureToken(boolean returnSecureToken) {
		this.returnSecureToken = returnSecureToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
}
