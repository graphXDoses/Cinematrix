package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.Context;
import com.texnologia_logismikou.Cinematrix.Controllers.ContextButtonController;

public class ContextButton extends Manager<ContextButtonController>
{
	
	public ContextButton(Context reference)
	{
		super.loadFXML("ContextButton");
		getController().setData(reference);
	}
	
}
