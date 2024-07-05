package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class MapField {

	private MapFieldsField mapValue;

	public MapField(Fields fields) {
		
		this.mapValue = new MapFieldsField(fields);
	}

	public MapFieldsField getMapValue() {
		return mapValue;
	}

	public void setMapValue(MapFieldsField mapValue) {
		this.mapValue = mapValue;
	}
	
	
	/**
	 * 	Turns an array of Fields to an array of Maps where each index is a set of fields.
	 * @param Fields[] an array of Fields
	 * @return MapField[] the array of Maps, one for each set of fields
	 */
	public static MapField[] toMapFieldArray(Fields[] fields) {
		
		MapField[] array = new MapField[fields.length];
		int i = 0;
		for(Fields field: fields) {
			
			array[i] = new MapField(field);
			i++;
		}
		
		return array;
	}
}
