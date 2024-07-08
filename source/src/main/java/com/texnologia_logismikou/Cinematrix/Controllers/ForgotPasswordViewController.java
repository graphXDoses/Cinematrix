package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.FileNotFoundException;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Views.LoginView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ForgotPasswordViewController {

    @FXML private TextField email_inputfield;
    @FXML private Label email_label;
    @FXML private Pane email_label_obs;
    @FXML private Button send_email_button;
    
    private int YOffset = 20;
    
    @FXML
    void initialize()
    {
    	toggleActiveHighlighted(email_inputfield, email_label, email_label_obs, false);
    	
    	email_inputfield.focusedProperty().addListener(new ChangeListener<Boolean>()
    	{
    		@Override
    		public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    		{
    			toggleActiveHighlighted(email_inputfield, email_label, email_label_obs, newPropertyValue);
    		}
    	});
    }
    
    void toggleActiveHighlighted(TextField referenceElement, Label lbl, Pane obs, boolean isFocused)
    {
    	if(!isFocused)
    	{
    		if(referenceElement.getText().isEmpty())
    		{
    			lbl.setTranslateY(YOffset);    			
    			obs.setStyle("-fx-background-color: transparent");
    		}
    		lbl.setStyle("-fx-text-fill: -fx-subtleBorderColor");
    	} else {
    		lbl.setTranslateY(0);
    		lbl.setStyle("-fx-text-fill: -fx-fg");
    		obs.setStyle("-fx-background-color: -fx-bg");
    	}
    }

    @FXML
    void sendEmailCallback(ActionEvent event)
    {
    	System.out.println("Sending password retrival email");
    }

    @FXML
    void switchToLoginCallback(ActionEvent event)
    {	
    	CinematrixAPI.getInstance()
    				.getActiveContext()
    				.promiseRedirectTo(null);
    	CinematrixAPI.getInstance().getMainDisplay().refresh();
    }

}
