package com.texnologia_logismikou.Cinematrix.Users;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;

public class Admin
extends User
{
	public Admin()
	{
		CinematrixAPI.ACCOUNT_CONTEXT
		 .setDefaultView(CinematrixAPI.ACCOUNT_CONTEXT.USER_DASHBOARD_VIEW);
	}
}
