package com.texnologia_logismikou.Cinematrix.Users;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.UserDocument;
import com.texnologia_logismikou.Cinematrix.DocumentObjects.Fields.UserFields;
import com.texnologia_logismikou.Cinematrix.Views.AllMoviesView;

public class UserCore {
	
	protected UserFields userFields;
	protected String uid;
	protected String firebaseId;

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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFirebaseId() {
		return firebaseId;
	}

	public void setFirebaseId(String firebaseId) {
		this.firebaseId = firebaseId;
	}

	public static UserCore createUser(UserDocument userDocument, String firebaseId, String uid) {
		User user;
		
		if(userDocument.getFields().getAdmin().getBooleanValue())
			user = new Admin();
		else
			user = new User();
		
		user.setUserFields(userDocument.getFields());
		user.setAccountCreationDate(userDocument.getCreateTime());
		user.setUid(uid);
		user.setFirebaseId(firebaseId);
		return(user);
	}
}
