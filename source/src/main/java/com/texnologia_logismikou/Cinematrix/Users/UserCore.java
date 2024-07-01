package com.texnologia_logismikou.Cinematrix.Users;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.UserFields;

public class UserCore {
	
	protected UserFields userFields;

	protected UserCore() {}
	
	protected UserCore(UserFields userFields) {
		
		this.userFields = userFields;
	}
	
	public UserFields getUserFields() {
		return userFields;
	}

	public void setUserFields(UserFields userFields) {
		this.userFields = userFields;
	}
}
