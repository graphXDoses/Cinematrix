package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class NameFieldArrayField {

	private ArrayField<NameField> arrayValue;

	public NameFieldArrayField(NameField[] arrayValues) {
		
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
