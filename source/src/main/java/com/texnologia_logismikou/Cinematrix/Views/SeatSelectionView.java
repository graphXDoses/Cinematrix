package com.texnologia_logismikou.Cinematrix.Views;

import com.texnologia_logismikou.Cinematrix.Controllers.SeatSelectionViewController;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderGuest;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderUser;

public class SeatSelectionView
extends View<SeatSelectionViewController>
implements VisibleUnderGuest, VisibleUnderUser
{

	public SeatSelectionView()
	{
		super.loadFXML("SeatSelectionView");
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
