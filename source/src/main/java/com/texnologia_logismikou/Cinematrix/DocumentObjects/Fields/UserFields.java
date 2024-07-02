package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class UserFields {

	private StringField email;
	private BooleanField admin;
	private StringField name;
	
	public UserFields(String name, String email, boolean admin) {
		
		this.email = new StringField(email);
		this.name = new StringField(name);
		this.admin = new BooleanField(admin);
	}
	
	public StringField getEmail() {
		return email;
	}
	public void setEmail(StringField email) {
		this.email = email;
	}
	public BooleanField getAdmin() {
		return admin;
	}
	public void setAdmin(BooleanField admin) {
		this.admin = admin;
	}
	public StringField getName() {
		return name;
	}
	public void setName(StringField name) {
		this.name = name;
	}
}
