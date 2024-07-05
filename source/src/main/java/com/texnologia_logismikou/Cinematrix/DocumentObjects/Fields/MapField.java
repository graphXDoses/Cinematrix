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
}
