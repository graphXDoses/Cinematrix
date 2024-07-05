package com.texnologia_logismikou.Cinematrix.RequestBodies;

public class SignInRequestBody {

	//For request
	private boolean returnSecureToken = true;
	private String email;
	private String password;
	
	public SignInRequestBody() {}
	
	public SignInRequestBody(String email, String password) {
		
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
