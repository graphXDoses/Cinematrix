package com.texnologia_logismikou.Cinematrix.Users;

import com.texnologia_logismikou.Cinematrix.DocumentObjects.UserDocument;
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

	public static UserCore createUser(UserDocument userDocument) {
		// TODO Auto-generated method stub
		User user;
		
		if(userDocument.getFields().getAdmin().getBooleanValue())
			user = new Admin();
		else
			user = new User();
		
		user.setUserFields(userDocument.getFields());
		user.setAccountCreationDate(userDocument.getCreateTime());
		return(user);
	}
}
