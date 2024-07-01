package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class CinemaArrayField {

	private ArrayField<NameField> arrayValue;

	public CinemaArrayField(NameField[] arrayValues) {
		
		ArrayField<NameField> arrayField = new ArrayField<NameField>(arrayValues);
		this.arrayValue = arrayField;
	}
	
	public ArrayField<NameField> getArrayValue() {
		return arrayValue;
	}

	public void setArrayValue(ArrayField<NameField> arrayValue) {
		this.arrayValue = arrayValue;
	}
}
