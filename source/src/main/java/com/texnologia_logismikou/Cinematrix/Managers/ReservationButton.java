package com.texnologia_logismikou.Cinematrix.Managers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Screening;
import com.texnologia_logismikou.Cinematrix.Controllers.ReservationButtonController;

public class ReservationButton extends Manager<ReservationButtonController>
{
	private Screening screening = null;
	public ReservationButton(Screening screening, LocalDateTime hour)
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
