package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class CinemaFields {

	/*
	 *  If you ever change the fields remember to update the QueryParameter!
	 */
	
	private StringField name;
	private StringField id;
	private StringField address;
	private StringField openHours;
	
	public StringField getName() {
		return name;
	}
	public void setName(StringField name) {
		this.name = name;
	}
	public StringField getId() {
		return id;
	}
	public void setId(StringField id) {
		this.id = id;
	}
	public StringField getAddress() {
		return address;
	}
	public void setAddress(StringField address) {
		this.address = address;
	}
	public StringField getOpenHours() {
		return openHours;
	}
	public void setOpenHours(StringField openHours) {
		this.openHours = openHours;
	}
}
