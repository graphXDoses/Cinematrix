package com.texnologia_logismikou.Cinematrix.Views;

import com.texnologia_logismikou.Cinematrix.Controllers.UserDashboardViewController;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderUser;

public class UserDashboardView
extends View<UserDashboardViewController>
implements VisibleUnderUser
{
	
	public UserDashboardView()
	{
		super.loadFXML("UserDashboardView");
	}

	@Override
	public void showToUser()
	{
		
	}

}
