package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class UserFields {

	private StringField email;
	private StringField name;
	private BooleanField admin;
	
	public UserFields(String name, String email, boolean isAdmin) {
		
		StringField anEmail = new StringField(email);
		StringField aName = new StringField(name);
		BooleanField anAdmin = new BooleanField(isAdmin);
		this.email = anEmail;
		this.name = aName;
		this.admin = anAdmin;
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
