package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class RoomFields extends Fields {

	private StringField roomId;
	private ArrayField<MapField> screenings;
	
	public RoomFields(String roomId, ScreeningFields[] screeningFields) {
		
		this.roomId = new StringField(roomId);
		
		this.screenings = new ArrayField<MapField>(MapField.toMapFieldArray(screeningFields));
	}
	
	public ArrayField<MapField> getScreenings() {
		return screenings;
	}

	public void setScreenings(ArrayField<MapField> screenings) {
		this.screenings = screenings;
	}

	public StringField getRoomId() {
		return roomId;
	}
	public void setRoomId(StringField roomId) {
		this.roomId = roomId;
	}
}
