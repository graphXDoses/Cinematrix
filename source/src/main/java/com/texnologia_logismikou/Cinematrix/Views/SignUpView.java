package com.texnologia_logismikou.Cinematrix.Views;

import com.texnologia_logismikou.Cinematrix.Controllers.SignUpViewController;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderGuest;

public class SignUpView
extends View<SignUpViewController>
implements VisibleUnderGuest
{

	public SignUpView()
	{
		super.loadFXML("SignUpView");
	}

	@Override
	public void showToGuest()
	{
		// TODO showToGuest implementation.
		
	}

}
