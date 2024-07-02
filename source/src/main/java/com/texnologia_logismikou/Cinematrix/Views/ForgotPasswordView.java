package com.texnologia_logismikou.Cinematrix.Views;

import com.texnologia_logismikou.Cinematrix.Controllers.ForgotPasswordViewController;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderGuest;

public class ForgotPasswordView
extends View<ForgotPasswordViewController>
implements VisibleUnderGuest
{
	public ForgotPasswordView()
	{
		super.loadFXML("ForgotPasswordView");
	}

	@Override
	public void showToGuest()
	{
		// TODO Auto-generated method stub
		
	}

}
