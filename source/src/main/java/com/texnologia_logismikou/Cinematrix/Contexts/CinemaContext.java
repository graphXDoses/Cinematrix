package com.texnologia_logismikou.Cinematrix.Contexts;

import com.texnologia_logismikou.Cinematrix.Views.NearCinemasView;
import com.texnologia_logismikou.Cinematrix.Views.View;

public class CinemaContext extends Context
{
	public static NearCinemasView NEAR_CINEMAS_VIEW = new NearCinemasView();
	
	public CinemaContext()
	{
		super("Cinemas", "images/theater.png");
		this.defaultView = NEAR_CINEMAS_VIEW;
		this.activeView = this.defaultView;
	}

}
