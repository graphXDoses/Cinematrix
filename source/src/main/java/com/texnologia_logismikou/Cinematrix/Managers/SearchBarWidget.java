package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.Controllers.SearchBarWidgetController;

public class SearchBarWidget
extends Manager<SearchBarWidgetController>
{
	public SearchBarWidget()
	{
		super.loadFXML("SearchBarWidget");
	}
	
	public void setPlaceholderText(String text)
	{
		getController().setPlaceholderText(text);
	}
	
	public void setVisible(boolean value)
	{
		getController().setVisibilityState(value);
	}
}
