package com.texnologia_logismikou.Cinematrix.Requests;

public class SignInRequest {

	//For request
	private boolean returnSecureToken;
	private String email;
	private String password;
	
	public SignInRequest(String email, String password) {
		
		this.email = email;
		this.password = password;
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
}
