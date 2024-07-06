package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class CinemaFields {

	/*
	 *  If you ever change the fields remember to update the QueryParameter!
	 */
	
	private StringField name;
	private StringField id;
	private StringField address;
	private StringField openHours;
	private DoubleField distance;
	
	public CinemaFields(String name, String id, String address, String openHours, int distance) {
		
		this.name = new StringField(name);
		this.id = new StringField(id);
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
	public DoubleField getDistance() {
		return distance;
	}
	public void setDistance(DoubleField distance) {
		this.distance = distance;
	}
}
