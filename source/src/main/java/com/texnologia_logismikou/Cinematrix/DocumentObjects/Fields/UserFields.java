package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class UserFields {

	private EmailField email;
	private NameField name;
	private AdminField admin;
	
	public UserFields(String name, String email, boolean isAdmin) {
		
		EmailField anEmail = new EmailField(email);
		NameField aName = new NameField(name);
		AdminField anAdmin = new AdminField(isAdmin);
		this.email = anEmail;
		this.name = aName;
		this.admin = anAdmin;
	}
	
	public EmailField getEmail() {
		return email;
	}
	public void setEmail(EmailField email) {
		this.email = email;
	}
	public NameField getName() {
		return name;
	}
	public void setName(NameField name) {
		
		this.name = name;
	}

	public AdminField getAdmin() {
		return admin;
	}

	public void setAdmin(AdminField admin) {
		this.admin = admin;
	}
}
