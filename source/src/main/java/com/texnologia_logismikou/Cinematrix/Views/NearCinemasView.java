package com.texnologia_logismikou.Cinematrix.Views;

import java.util.Collections;

import com.texnologia_logismikou.Cinematrix.Cinema;
import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Contexts.Context;
import com.texnologia_logismikou.Cinematrix.Controllers.NearCinemasViewController;
import com.texnologia_logismikou.Cinematrix.Managers.CinemaModal;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderAdmin;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderGuest;
import com.texnologia_logismikou.Cinematrix.Users.VisibilityPolicies.VisibleUnderUser;

public class NearCinemasView
extends View<NearCinemasViewController>
implements VisibleUnderGuest, VisibleUnderUser, VisibleUnderAdmin
{

	public NearCinemasView()
	{
		super.loadFXML("NearCinemasView");
	}

	
	void standardPrepare()
	{
//		Context cinemas = CinematrixAPI.getInstance().getActiveContext();
//		if(cinemas.equals(CinematrixAPI.getInstance().getContexts().get(1)))
//		{			
//			CinematrixAPI.getInstance().getCinemas().forEach(cinema->
//			{
//				getController().appendCinemaModal(cinema.getModal());			
//			});
//		} else {
//			Movie thisMovie = ((MovieDetailsView)CinematrixAPI.getInstance()
//					.getActiveContext().getActiveView()).getSelectedMovie();
//			CinematrixAPI.getInstance()
//			.getCinemas()
//			.stream().filter(cinema->{
//				return(cinema.isScreeningTheMovie(thisMovie));
//			}).forEach(cinema->{
//				getController().appendCinemaModal(cinema.getModal());
//			});
//		}
		if(CinematrixAPI.getInstance().getActiveContext().equals(CinematrixAPI.MOVIE_CONTEXT))
		{
			Movie thisMovie = ((MovieDetailsView)CinematrixAPI.getInstance()
					.getActiveContext().getActiveView()).getSelectedMovie();
			thisMovie.getAssociateScreenings().forEach(screening->{
				CinemaModal modal = new CinemaModal();
				modal.getController().setData(screening.getCinema());
				modal.getController().setScreeningData(screening);
				getController().appendCinemaModal(modal);
			});
		} else {
			CinematrixAPI.getInstance().getCinemas().forEach(cinema->
			{
				getController().appendCinemaModal(cinema.getModal());			
			});
		}
	}


	@Override
	public void showToAdmin()
	{
		standardPrepare();
	}


	@Override
	public void showToUser()
	{
		standardPrepare();
	}


	@Override
	public void showToGuest()
	{
		standardPrepare();
	}
}
