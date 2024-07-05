package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class RoomFields extends Fields {

	/*
	 * 	If you add a new field remember to update the QueryParameter.
	 */
	
	private StringField id;
	private ArrayField<MapField> screenings;
	private DoubleField seats;
	
	public RoomFields(String roomId, ScreeningFields[] screeningFields, int seats) {
		
		this.id = new StringField(roomId);
		this.seats = new DoubleField(seats);
		this.screenings = new ArrayField<MapField>(MapField.toMapFieldArray(screeningFields));
	}
	
	public ArrayField<MapField> getScreenings() {
		return screenings;
	}

	public void setScreenings(ArrayField<MapField> screenings) {
		this.screenings = screenings;
	}

	public StringField getRoomId() {
		return id;
	}
	public void setRoomId(StringField roomId) {
		this.id = roomId;
	}

	public StringField getId() {
		return id;
	}

	public void setId(StringField id) {
		this.id = id;
	}

	public DoubleField getSeats() {
		return seats;
	}

	public void setSeats(DoubleField seats) {
		this.seats = seats;
	}
}
