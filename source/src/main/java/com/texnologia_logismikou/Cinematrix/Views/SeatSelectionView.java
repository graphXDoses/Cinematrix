package com.texnologia_logismikou.Cinematrix.Views;

import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Controllers.SeatSelectionViewController;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderGuest;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderUser;

public class SeatSelectionView
extends View<SeatSelectionViewController>
implements VisibleUnderGuest, VisibleUnderUser
{
	private Movie selectedMovie = null;

	public SeatSelectionView(Movie referenceMovie)
	{
		super.loadFXML("SeatSelectionView");
		selectedMovie = referenceMovie;
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
