package com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields;

import com.texnologia_logismikou.Cinematrix.VenueSoundSystem;
import com.texnologia_logismikou.Cinematrix.VenueTopdownType;
import com.texnologia_logismikou.Cinematrix.VenueTraits;

public class VenueFields extends Fields {

	/*
	 * 	If you add a new field remember to update the QueryParameter.
	 */
	
	private StringField uid;
	private StringField name;
	private StringField topDownType;
	private StringField soundSystem;
	private StringField traits;
	
	public VenueFields(String name, VenueTopdownType topdownType, VenueTraits traits
			, VenueSoundSystem soundSystem) {
		
		this.name = new StringField(name);
		name = StringField.toPascalCase(name);
		String uid = name.toLowerCase() + "_" + System.currentTimeMillis();
		this.uid = new StringField(uid);
		this.topDownType = new StringField(topdownType.name());
		this.soundSystem = new StringField(soundSystem.name());
		this.traits = new StringField(traits.name());
	}

	public StringField getUid() {
		return uid;
	}
	public void setUid(StringField id) {
		this.uid = id;
	}
	public StringField getName() {
		return name;
	}

	public void setName(StringField name) {
		this.name = name;
	}

	public StringField getTopDownType() {
		return topDownType;
	}

	public void setTopDownType(StringField topDownType) {
		this.topDownType = topDownType;
	}

	public StringField getSoundSystem() {
		return soundSystem;
	}

	public void setSoundSystem(StringField soundSystem) {
		this.soundSystem = soundSystem;
	}

	public StringField getTraits() {
		return traits;
	}

	public void setTraits(StringField traits) {
		this.traits = traits;
	}
}
