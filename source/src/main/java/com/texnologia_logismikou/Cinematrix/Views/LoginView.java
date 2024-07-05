package com.texnologia_logismikou.Cinematrix.Views;

import com.texnologia_logismikou.Cinematrix.Controllers.LoginViewController;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderGuest;

public class LoginView
extends View<LoginViewController>
implements VisibleUnderGuest
{

	public LoginView()
	{
		super.loadFXML("LoginView");
	}

	@Override
	public void showToGuest()
	{
		System.out.println("SHOWING TO GUEST METHOD");
	}

}
