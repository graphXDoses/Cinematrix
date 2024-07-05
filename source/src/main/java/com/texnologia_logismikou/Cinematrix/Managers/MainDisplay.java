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
		
		try {
			view.prepare();
			getController().setContent(view.getParent());
		} catch (ClassNotFoundException e) {
			CinematrixAPI.getInstance()
						 .getActiveContext()
						 .promiseRedirectTo(null);
		}
	}
}
