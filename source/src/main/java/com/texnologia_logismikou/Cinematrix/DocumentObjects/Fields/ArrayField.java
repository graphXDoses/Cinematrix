package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class ArrayField<T> {

	private T[] values;

	public ArrayField(T[] arrayValues) {
		
		this.values = arrayValues;
	}
	
	public T[] getValues() {
		return values;
	}

	public void setValues(T[] values) {
		this.values = values;
	}
}
