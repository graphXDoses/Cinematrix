package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class TimestampField {

	private String timestampValue;

	public TimestampField(String timestamp) {
		
		this.timestampValue = timestamp;
	}
	
	public static String toTimestamp(String timestamp) {
		
		String regex = "[\\s]";
		String[] array = timestamp.split(regex);
		String timestampFormatted = "";
		
		int i = 0;
		for(String field: array) {
			timestampFormatted += field;
			switch(i) {
			case 0, 1: timestampFormatted += "-"; break;
			case 2: timestampFormatted += "T"; break;
			case 3, 4: timestampFormatted += ":"; break;
			case 5: timestampFormatted += "Z"; break;
			}
			i++;
		}
		
		System.out.println(timestampFormatted);
		
		return timestampFormatted;
	}
	
	public String getTimestampValue() {
		return timestampValue;
	}

	public void setTimestampValue(String timestampValue) {
		this.timestampValue = timestampValue;
	}
}
