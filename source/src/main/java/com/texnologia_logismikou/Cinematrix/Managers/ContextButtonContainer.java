package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.CinemaSystem;
import com.texnologia_logismikou.Cinematrix.Context;
import com.texnologia_logismikou.Cinematrix.Controllers.ContextButtonContainerController;

public class ContextButtonContainer extends Manager<ContextButtonContainerController>{
	
	
	
	public ContextButtonContainer()
	{
		super.loadFXML("ContextButtonContainer");

		CinemaSystem.Invoke()
					.getContexts()
					.forEach((c)-> { getController().addContextButton(c.getButton()); });
	}
	
}
