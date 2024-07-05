package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class ArrayField<T> extends Field {

	private ArrayValuesField<T> arrayValue;

	public ArrayField(T[] arrayValues) {
		
		ArrayValuesField<T> arrayField = new ArrayValuesField<T>(arrayValues);
		this.arrayValue = arrayField;
	}
	
	public ArrayValuesField<T> getArrayValue() {
		return arrayValue;
	}

	public void setArrayValue(ArrayValuesField<T> arrayValue) {
		this.arrayValue = arrayValue;
	}
}
