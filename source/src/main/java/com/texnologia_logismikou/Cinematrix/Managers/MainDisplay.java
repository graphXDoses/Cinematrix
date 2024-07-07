package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Views.View;

public class MainDisplay extends DisplayManager
{	
	@Override
	public void refresh()
	{
		View view = CinematrixAPI.getInstance()
								.getActiveContext()
								.getActiveView();
		
		view.prepare();
		getController().setContent(view.getParent());
	}
}
