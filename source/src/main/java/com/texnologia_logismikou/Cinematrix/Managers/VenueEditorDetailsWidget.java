package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.Controllers.VenueEditorDetailsWidgetController;

public class VenueEditorDetailsWidget
extends Manager<VenueEditorDetailsWidgetController>
{
	public VenueEditorDetailsWidget()
	{
		super.loadFXML("VenueEditorDetailsWidget");
	}
}
