package com.texnologia_logismikou.Cinematrix.Contexts;

import com.texnologia_logismikou.Cinematrix.Views.ForgotPasswordView;
import com.texnologia_logismikou.Cinematrix.Views.LoginView;
import com.texnologia_logismikou.Cinematrix.Views.SignUpView;
import com.texnologia_logismikou.Cinematrix.Views.UserDashboardView;
import com.texnologia_logismikou.Cinematrix.Views.View;

public class AccountContext extends Context
{
	public static LoginView LOGIN_VIEW = new LoginView();
	public static SignUpView SIGNUP_VIEW = new SignUpView();
	public static UserDashboardView USER_DASHBOARD_VIEW = new UserDashboardView();
	public static ForgotPasswordView FORGOT_PASSWORD_VIEW = new ForgotPasswordView();

	public AccountContext()
	{
		super("Account", "images/account.png");
		this.defaultView = LOGIN_VIEW;
		this.activeView  = this.defaultView;
	}

}
