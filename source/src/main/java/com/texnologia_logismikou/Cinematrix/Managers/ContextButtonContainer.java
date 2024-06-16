package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.Context;
import com.texnologia_logismikou.Cinematrix.Controllers.ContextButtonContainerController;

public class ContextButtonContainer extends Manager<ContextButtonContainerController>{
	
	public ContextButtonContainer()
	{
		super.loadFXML("ContextButtonContainer");

		Context Movies = new Context("Movies", "images/movie.png");
		getController().addContextButton(Movies.getButton());
		
		Context Cinemas = new Context("Cinemas", "images/theater.png");
		getController().addContextButton(Cinemas.getButton());
		
		Context Account = new Context("Account", "images/account.png");
		getController().addContextButton(Account.getButton());
	}
	
}
