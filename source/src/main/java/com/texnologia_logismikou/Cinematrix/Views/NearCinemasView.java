package com.texnologia_logismikou.Cinematrix.Views;

import java.util.Collections;

import com.texnologia_logismikou.Cinematrix.Cinema;
import com.texnologia_logismikou.Cinematrix.CinemaSystem;
import com.texnologia_logismikou.Cinematrix.Context;
import com.texnologia_logismikou.Cinematrix.Movie;
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
		Context cinemas = CinemaSystem.getInstance().getActiveContext();
		if(cinemas.equals(CinemaSystem.getInstance().getContexts().get(1)))
		{			
			CinemaSystem.getInstance().getCinemas().forEach(cinema->
			{
				getController().appendCinemaModal(cinema.getModal());			
			});
		} else {
			Movie thisMovie = ((MovieDetailsView)CinemaSystem.getInstance()
					.getActiveContext().getActiveView()).getSelectedMovie();
			CinemaSystem.getInstance()
			.getCinemas()
			.stream().filter(cinema->{
				return(false);
			}).forEach(cinema->{
				getController().appendCinemaModal(cinema.getModal());
			});
		}
	}
}
