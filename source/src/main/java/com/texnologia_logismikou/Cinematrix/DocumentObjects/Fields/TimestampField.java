package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

import java.time.LocalDateTime;

public class TimestampField {

	private String timestampValue;

	public TimestampField(String timestamp) {
		
		this.timestampValue = timestamp;
	}
	
	public static LocalDateTime toLocalDateTime(String value) {
		
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		
		String[] temp = value.split("T");
		String[] temp2 = temp[0].split("-");
		int counter = 0;
		for(String j: temp2) {
			switch(counter) {
			case 0: year = Integer.parseInt(j);
			case 1: month = Integer.parseInt(j);
			case 2: day = Integer.parseInt(j);
			}
			counter++;
		}
		counter = 0;
		String temp3 = temp[1].substring(0, temp[1].length() -1);
		temp2 = temp3.split(":");
		for(String j: temp2) {
			switch(counter) {
			case 0: hour = Integer.parseInt(j);
			case 1: minute = Integer.parseInt(j);
			}
			counter++;
		}
		
		return LocalDateTime.of(year, month, day, hour, minute);
	}
	
	public String getTimestampValue() {
		return timestampValue;
	}

	public void setTimestampValue(String timestampValue) {
		this.timestampValue = timestampValue;
	}
}
