package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class CinemaFields extends Fields {

	/*
	 *  If you ever change the fields remember to update the QueryParameter!
	 */
	
	private StringField name;
	private StringField uid;
	private StringField address;
	private StringField openHours; // TODO replace with a better timestamp-like value intsead of a string.
	private DoubleField distance;
	
	public CinemaFields() {}
	
	public CinemaFields(String name, String address, String openHours, double distance) {
		
		this.name = new StringField(name);
		this.uid = new StringField(name.toLowerCase() + "_" + System.currentTimeMillis());
		this.address = new StringField(address);
		this.openHours = new StringField(openHours);
		this.distance = new DoubleField(distance);
	}
	
	public StringField getName() {
		return name;
	}
	public void setName(StringField name) {
		this.name = name;
	}
	public StringField getUid() {
		return uid;
	}
	public void setUid(StringField uid) {
		this.uid = uid;
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
	public DoubleField getDistance() {
		return distance;
	}
	public void setDistance(DoubleField distance) {
		this.distance = distance;
	}
}
