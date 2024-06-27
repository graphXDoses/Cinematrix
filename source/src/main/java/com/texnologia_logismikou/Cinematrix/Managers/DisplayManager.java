package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.Controllers.DisplayAreaController;

public abstract class DisplayManager extends Manager<DisplayAreaController> {
	public DisplayManager()
	{
		super.loadFXML("DisplayArea");
	}
	
	public abstract void refresh();
}
