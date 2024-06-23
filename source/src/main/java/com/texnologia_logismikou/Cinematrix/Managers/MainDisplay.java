package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.CinemaSystem;
import com.texnologia_logismikou.Cinematrix.Controllers.DisplayAreaController;
import com.texnologia_logismikou.Cinematrix.Views.View;

public class MainDisplay extends Manager<DisplayAreaController>
{
	public MainDisplay()
	{
		super.loadFXML("DisplayArea");
	}
	
	public void refresh()
	{
		View view = CinemaSystem.getInstance()
								.getActiveContext()
								.getActiveView();
		
		view.prepare();
		getController().setContent(view.getParent());
	}
}
