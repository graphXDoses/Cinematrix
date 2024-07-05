package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

public class BooleanField extends Field {
	
	private boolean booleanValue;

	public BooleanField(boolean booleanValue) {
		
		this.booleanValue = booleanValue;
	}

	public boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
}
