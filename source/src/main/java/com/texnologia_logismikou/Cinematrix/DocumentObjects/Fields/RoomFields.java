package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class RoomFields extends Fields {

	private StringField roomId;
	private MapField screenings;
	
	public RoomFields(String roomId, Fields screeningFields) {
		
		this.roomId = new StringField(roomId);
		this.screenings = new MapField(screeningFields);
	}
	
	public StringField getRoomId() {
		return roomId;
	}
	public void setRoomId(StringField roomId) {
		this.roomId = roomId;
	}
	public MapField getScreenings() {
		return screenings;
	}
	public void setScreenings(MapField screenings) {
		this.screenings = screenings;
	}
}
