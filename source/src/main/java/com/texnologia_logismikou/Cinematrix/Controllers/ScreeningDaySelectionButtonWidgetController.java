package com.texnologia_logismikou.Cinematrix.Controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ScreeningDaySelectionButtonWidgetController
{

	@FXML private Label date_number_label;
	@FXML private Label day_label;
	@FXML private Label month_label;
	@FXML private Pane root;

    @FXML
    void selectScreeningDayCallback(ActionEvent event)
    {
    	System.out.println("Date Selected!");
    }

	public void setData(LocalDate date)
	{
		// Define a DateTimeFormatter for the desired format
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH);
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d", Locale.ENGLISH);

        // Format the day, month, and date
        String day = date.format(dayFormatter).toUpperCase();
        String month = date.format(monthFormatter).toUpperCase();
        String dayOfMonth = date.format(dateFormatter);

		day_label.setText(date.equals(LocalDate.now()) ? "TODAY" : day);
		month_label.setText(month);
		date_number_label.setText(dayOfMonth);
	}

}
