package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.Controllers.ReservationButtonController;

public class ReservationButton extends Manager<ReservationButtonController>
{
	public ReservationButton(String hour)
	{
		super.loadFXML("ReservationButton");
		getController().setHour(hour);
	}
}
