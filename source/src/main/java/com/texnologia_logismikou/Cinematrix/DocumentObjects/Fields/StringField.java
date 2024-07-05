package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class StringField {

	private String stringValue;

	public StringField(String string) {
		
		this.stringValue = string;
	}
	
	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	
	
	/**
	 * Turns a string array into a string field array.
	 * @author Phoebus
	 * @param array a string array
	 * @return StringField[] the string field array created from the strings of the parameter.
	 */
	public static StringField[] toStringFieldArray(String[] array) {
		
		StringField[] fieldArray = new StringField[array.length];
		int i = 0;
		for(String value: array) {
			fieldArray[i] = new StringField(value);
			i++;
		}
		
		return fieldArray;
	}
}
