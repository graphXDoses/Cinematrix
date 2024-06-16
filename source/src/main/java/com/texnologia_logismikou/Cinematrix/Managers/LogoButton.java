package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.Controllers.LogoButtonController;

public class LogoButton extends Manager<LogoButtonController>
{
	public LogoButton()
	{
		super.loadFXML("LogoButton");
	}
}
