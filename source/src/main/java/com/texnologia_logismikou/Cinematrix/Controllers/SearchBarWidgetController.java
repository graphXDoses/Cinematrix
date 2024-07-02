package com.texnologia_logismikou.Cinematrix.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SearchBarWidgetController
{
	@FXML private HBox root;
    @FXML private TextField querry_field;
    @FXML private Button search_button;

    @FXML
    void commenceSearchCallback(ActionEvent event)
    {
    	System.out.println("Commencing search for --> \'" + querry_field.getText() + "\'");
    	querry_field.setText("");
    }

	public void setVisibilityState(boolean value)
	{
		root.setVisible(value);
	}

	public void setPlaceholderText(String text)
	{
		querry_field.setPromptText(text);
	}
}