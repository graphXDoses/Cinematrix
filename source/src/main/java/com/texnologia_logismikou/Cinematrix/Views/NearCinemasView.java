package com.texnologia_logismikou.Cinematrix.Views;

import java.util.Collections;

import com.texnologia_logismikou.Cinematrix.Cinema;
import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Movie;
import com.texnologia_logismikou.Cinematrix.Contexts.Context;
import com.texnologia_logismikou.Cinematrix.Controllers.NearCinemasViewController;
import com.texnologia_logismikou.Cinematrix.Managers.CinemaModal;

public class NearCinemasView extends View<NearCinemasViewController> {

	public NearCinemasView()
	{
		super.loadFXML("NearCinemasView");
	}
	
	@Override
	public void prepare()
	{
		Context cinemas = CinematrixAPI.getInstance().getActiveContext();
		if(cinemas.equals(CinematrixAPI.getInstance().getContexts().get(1)))
		{			
			CinematrixAPI.getInstance().getCinemas().forEach(cinema->
			{
				getController().appendCinemaModal(cinema.getModal());			
			});
		} else {
			Movie thisMovie = ((MovieDetailsView)CinematrixAPI.getInstance()
					.getActiveContext().getActiveView()).getSelectedMovie();
			CinematrixAPI.getInstance()
			.getCinemas()
			.stream().filter(cinema->{
				return(cinema.isScreeningTheMovie(thisMovie));
			}).forEach(cinema->{
				getController().appendCinemaModal(cinema.getModal());
			});
		}
	}
}
