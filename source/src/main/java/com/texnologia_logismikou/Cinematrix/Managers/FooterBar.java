package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.Controllers.FooterBarController;

public class FooterBar extends Manager<FooterBarController>
{
	public FooterBar()
	{
		super.loadFXML("FooterBar");
	}
}
