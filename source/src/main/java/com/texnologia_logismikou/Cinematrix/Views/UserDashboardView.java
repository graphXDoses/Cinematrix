package com.texnologia_logismikou.Cinematrix.Views;

import com.texnologia_logismikou.Cinematrix.Controllers.UserDashboardViewController;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderAdmin;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderUser;

public class UserDashboardView
extends View<UserDashboardViewController>
implements VisibleUnderUser, VisibleUnderAdmin
{
	
	public UserDashboardView()
	{
		super.loadFXML("UserDashboardView");
	}

	@Override
	public void showToUser()
	{
		getController().setData();
	}

	@Override
	public void showToAdmin()
	{
		
	}

}
