package com.texnologia_logismikou.Cinematrix.Views;

import java.time.LocalDateTime;

import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Screening;
import com.texnologia_logismikou.Cinematrix.Controllers.SeatSelectionViewController;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderGuest;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderUser;

public class SeatSelectionView
extends View<SeatSelectionViewController>
implements VisibleUnderGuest, VisibleUnderUser
{
	private Screening screening = null;
//	private Movie selectedMovie = null;

	public SeatSelectionView(Screening screening, LocalDateTime hour)
	{
		super.loadFXML("SeatSelectionView");
		this.screening = screening;
		getController().setData(screening, hour);
	}
	/*
	public Screening getAssociateScreening()
	{
		return(this.screening);
	}
	*/

	@Override
	public void showToUser()
	{
		// TODO showToUser implementation.
		
	}

	@Override
	public void showToGuest()
	{
		// TODO showToGuest implementation.
		
	}

}
