package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class CinemaFields {

	/*
	 *  If you ever change the fields remember to update the QueryParameter!
	 */
	
	private StringField name;
	private StringField uid;
	private StringField address;
	private StringField openHours;
	private DoubleField distance;
	
	public CinemaFields(String name, String address, String openHours, double distance) {
		
		this.name = new StringField(name);
		this.uid = new StringField(name + "_" + System.currentTimeMillis());
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
