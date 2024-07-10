package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.Controllers.CategoryBubbleWidgetController;

public class CategoryBubbleWidget
extends Manager<CategoryBubbleWidgetController>
{
	public CategoryBubbleWidget(String cat_name)
	{
		super.loadFXML("CategoryBubbleWidget");
		getController().setData(cat_name);
	}
	
	public CategoryBubbleWidget()
	{
		super.loadFXML("CategoryBubbleWidget");
	}
}
