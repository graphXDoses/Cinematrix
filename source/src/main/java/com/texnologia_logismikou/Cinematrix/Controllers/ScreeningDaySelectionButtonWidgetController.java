package com.texnologia_logismikou.Cinematrix.Controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ScreeningDaySelectionButtonWidgetController
{

	@FXML private Label date_number_label;
	@FXML private Label day_label;
	@FXML private Label month_label;
	@FXML private Pane root;
	
	private static final String INACTIVE_STATE = "movie_details_button_inactive";
	private static final String ACTIVE_STATE   = "movie_details_button_active";
	
	public void selectThis()
	{
		setActiveState(root, ACTIVE_STATE);
    	if(root.getParent() != null && root.getParent() instanceof HBox)
    	{
    		((HBox)root.getParent()).getChildren().filtered(button->{
    			return(!button.equals(root));
    		}).forEach(button->{
    			setActiveState((Pane)button, INACTIVE_STATE);
    		});
    	}
	}

    @FXML
    void selectScreeningDayCallback(ActionEvent event)
    {
    	selectThis();
    }
    
    private void setActiveState(Pane rootNode, String state)
    {
    	rootNode.getStyleClass().clear();
    	rootNode.getStyleClass().add(state);
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
