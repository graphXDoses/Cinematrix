package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class UserFields {

	private String email;
	private String name;
	private boolean admin;
	
	public UserFields(String name, String email, boolean isAdmin) {
		
		this.email = email;
		this.name = name;
		this.admin = isAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
