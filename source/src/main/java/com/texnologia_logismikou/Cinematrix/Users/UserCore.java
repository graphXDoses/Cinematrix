package com.texnologia_logismikou.Cinematrix.Users;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.UserDocument;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.UserFields;
import com.texnologia_logismikou.Cinematrix.Views.AllMoviesView;

public class UserCore {
	
	protected UserFields userFields;

	protected UserCore() {}
	
	protected UserCore(UserFields userFields)
	{	
		this.userFields = userFields;
		CinematrixAPI.getInstance().MOVIE_CONTEXT.ALL_MOVIES_VIEW = new AllMoviesView();
	}
	
	public UserFields getUserFields() {
		return userFields;
	}

	public void setUserFields(UserFields userFields) {
		this.userFields = userFields;
	}

	public static UserCore createUser(UserDocument userDocument) {
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
