package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class TimestampField {

	private String timestampValue;

	public TimestampField(String timestamp) {
		
		this.timestampValue = timestamp;
	}
	
	public static String toTimestamp(String year, String month, String day, String hour, String minute) {
		String timestampValue = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":00" + "Z";
		return timestampValue;
	}
	
	public String getTimestampValue() {
		return timestampValue;
	}

	public void setTimestampValue(String timestampValue) {
		this.timestampValue = timestampValue;
	}
}
