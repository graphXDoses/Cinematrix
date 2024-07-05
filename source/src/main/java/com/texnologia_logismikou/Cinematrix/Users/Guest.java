package com.texnologia_logismikou.Cinematrix.Users;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;

public class Guest
extends UserCore
{
	public Guest()
	{
		CinematrixAPI.ACCOUNT_CONTEXT
		 .setDefaultView(CinematrixAPI.ACCOUNT_CONTEXT.LOGIN_VIEW);
	}
	
	@Override
	public String toString()
	{
		return(getClass().getSimpleName().toUpperCase());
	}
}
