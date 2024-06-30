package com.texnologia_logismikou.Cinematrix.ResponseBodies;

public class ErrorResponseBody {

	// Sign In error class.
	
	private String message;
	private int code;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
