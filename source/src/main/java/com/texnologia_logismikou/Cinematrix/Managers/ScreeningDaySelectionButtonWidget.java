package com.texnologia_logismikou.Cinematrix.Managers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.texnologia_logismikou.Cinematrix.Controllers.ScreeningDaySelectionButtonWidgetController;

public class ScreeningDaySelectionButtonWidget
extends Manager<ScreeningDaySelectionButtonWidgetController>
{
	public ScreeningDaySelectionButtonWidget(LocalDate date)
	{
		super.loadFXML("ScreeningDaySelectionButtonWidget");
		getController().setData(date);
	}
}
