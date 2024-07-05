package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class MapFieldsField<T extends Fields> extends Field {

	private T fields;
	
	public MapFieldsField(T fields) {
		
		this.fields = fields;
	}

	public T getFields() {
		return fields;
	}

	public void setFields(T fields) {
		this.fields = fields;
	}
}
