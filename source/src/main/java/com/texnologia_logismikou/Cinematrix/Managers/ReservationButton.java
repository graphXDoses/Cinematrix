package com.texnologia_logismikou.Cinematrix.Managers;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Screening;
import com.texnologia_logismikou.Cinematrix.Controllers.ReservationButtonController;

public class ReservationButton extends Manager<ReservationButtonController>
{
	private Screening screening = null;
	public ReservationButton(Screening screening, String hour)
	{
		super.loadFXML("ReservationButton");
		getController().setData(screening, hour);
		this.screening = screening;
	}
	
	public Screening getAssociateScreening()
	{
		return(screening);
	}
}
