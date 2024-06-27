package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.Controllers.CinemaModalController;

public class CinemaModal extends Manager<CinemaModalController> {

	public CinemaModal()
	{
		super.loadFXML("CinemaModal");
	}
	
}
