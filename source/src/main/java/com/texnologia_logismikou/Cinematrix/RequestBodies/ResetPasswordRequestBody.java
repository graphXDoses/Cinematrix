package com.texnologia_logismikou.Cinematrix.RequestBodies;

public class ResetPasswordRequestBody {

	private String email;
	private String requestType;
	
	public ResetPasswordRequestBody(String email) {
		
		this.email = email;
		this.requestType = "PASSWORD_RESET";
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
