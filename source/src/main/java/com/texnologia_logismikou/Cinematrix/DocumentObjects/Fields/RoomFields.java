package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class RoomFields extends Fields {

	private StringField roomId;
	private MapField<ScreeningFields> screenings;
	
	public RoomFields(String roomId, ScreeningFields screeningFields) {
		
		this.roomId = new StringField(roomId);
		this.screenings = new MapField<ScreeningFields>(screeningFields);
	}
	
	public StringField getRoomId() {
		return roomId;
	}
	public void setRoomId(StringField roomId) {
		this.roomId = roomId;
	}
	public MapField<ScreeningFields> getScreenings() {
		return screenings;
	}
	public void setScreenings(MapField<ScreeningFields> screenings) {
		this.screenings = screenings;
	}
}
