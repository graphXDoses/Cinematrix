package com.texnologia_logismikou.Cinematrix.Users;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Views.AllMoviesView;
import com.texnologia_logismikou.Cinematrix.Views.NearCinemasView;

public class User
extends Guest
{
	private String accountCreationDate; 
	
	public User()
	{
		CinematrixAPI.ACCOUNT_CONTEXT
					 .setDefaultView(CinematrixAPI.ACCOUNT_CONTEXT.USER_DASHBOARD_VIEW);
	}
	
	protected void setAccountCreationDate(String date)
	{
		accountCreationDate = date;
	}
	
	public String getAccountCreationDate()
	{
		// Parse the input date string
        OffsetDateTime dateTime = OffsetDateTime.parse(accountCreationDate);
        
        // Define the desired output format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        
        // Format the parsed date
        return (dateTime.format(formatter));
	}
}
