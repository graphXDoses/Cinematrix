package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class MapField<T extends Fields> extends Field {

	private MapFieldsField<T> mapValue;

	public MapField(T fields) {
		
		this.mapValue = new MapFieldsField<T>(fields);
	}

	public MapFieldsField<T> getMapValue() {
		return mapValue;
	}

	public void setMapValue(MapFieldsField<T> mapValue) {
		this.mapValue = mapValue;
	}
}
