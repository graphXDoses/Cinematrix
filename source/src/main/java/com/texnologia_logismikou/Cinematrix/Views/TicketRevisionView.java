package com.texnologia_logismikou.Cinematrix.Views;

import com.texnologia_logismikou.Cinematrix.Controllers.TicketRevisionViewController;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderGuest;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderUser;

public class TicketRevisionView
extends View<TicketRevisionViewController>
implements VisibleUnderGuest, VisibleUnderUser
{
	public TicketRevisionView()
	{
		super.loadFXML("TicketRevisionView");
	}

	@Override
	public void showToUser()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showToGuest()
	{
		// TODO Auto-generated method stub
		
	}
}
