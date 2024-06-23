package com.texnologia_logismikou.Cinematrix.Views;

import com.texnologia_logismikou.Cinematrix.Cinema;
import com.texnologia_logismikou.Cinematrix.CinemaSystem;
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
		CinemaSystem.getInstance().getCinemas().forEach(cinema->
		{
			getController().appendCinemaModal(cinema.getModal());			
		});
	}
}
