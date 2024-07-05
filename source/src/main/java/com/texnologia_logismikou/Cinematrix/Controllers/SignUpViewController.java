package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.FileNotFoundException;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.ResponseBodies.ErrorResponseBody;
import com.texnologia_logismikou.Cinematrix.Views.LoginView;
import com.texnologia_logismikou.Cinematrix.Views.SignUpView;
import com.texnologia_logismikou.Cinematrix.Views.UserDashboardView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SignUpViewController
{
	
	@FXML private TextField email_inputfield;
	@FXML private Label email_label;
	@FXML private Pane email_label_obs;
	
	@FXML private PasswordField pass_inputfield;
	@FXML private Label pass_label;
	@FXML private Pane pass_label_obs;
	
	@FXML private TextField name_inputfield;
    @FXML private Label name_label;
    @FXML private Pane name_label_obs;
    
    @FXML private Button signup_button;
    
    @FXML private AnchorPane error_message_container;
	@FXML private Label error_message_label;
	
	private int YOffset = 20;

    @FXML
    void initialize()
    {
    	toggleActiveHighlighted(name_inputfield, name_label, name_label_obs, false);
    	toggleActiveHighlighted(email_inputfield, email_label, email_label_obs, false);
    	toggleActiveHighlighted(pass_inputfield, pass_label, pass_label_obs, false);
    	hideErrorMessageContainer();
    	
    	name_inputfield.focusedProperty().addListener(new ChangeListener<Boolean>()
    	{
    		@Override
    		public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    		{
    			toggleActiveHighlighted(name_inputfield, name_label, name_label_obs, newPropertyValue);
    		}
    	});
    	
    	email_inputfield.focusedProperty().addListener(new ChangeListener<Boolean>()
    	{
    		@Override
    		public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    		{
    			toggleActiveHighlighted(email_inputfield, email_label, email_label_obs, newPropertyValue);
    		}
    	});
    	
    	pass_inputfield.focusedProperty().addListener(new ChangeListener<Boolean>()
    	{
    		@Override
    		public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    		{
    			toggleActiveHighlighted(pass_inputfield, pass_label, pass_label_obs, newPropertyValue);
    		}
    	});
    }
    
    void hideErrorMessageContainer() { error_message_container.setVisible(false); }
    void revealErrorMessageContainer(String error_message)
    {
    	error_message_container.setVisible(true);
    	error_message_label.setText(error_message);
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
    void signupCallback(ActionEvent event)
    {
    	ErrorResponseBody error = null;
    	
    	/*
    	 *  Add checks for inputs.
    	 */
    	
    	error = CinematrixAPI.getInstance().userSignUp(name_inputfield.getText(), email_inputfield.getText(), pass_inputfield.getText());
    	if(error != null) {
    		// Display the error in a textbox and refresh page?
//    		System.out.println(error.getMessage());
    		String error_message_string = error.getMessage();
    		revealErrorMessageContainer
    		(
    				"Error signing up. Details: " +
    				error_message_string
			);
    		return;
    	}
    	CinematrixAPI.getInstance()
    				.getActiveContext()
    				.promiseRedirectTo(CinematrixAPI.ACCOUNT_CONTEXT.USER_DASHBOARD_VIEW);
    	CinematrixAPI.getInstance().getMainDisplay().refresh();
    }
    
    @FXML
    void switchToLoginCallback(ActionEvent event)
    {
    	CinematrixAPI.getInstance()
    				.getActiveContext()
    				.promiseRedirectTo(null);
    	CinematrixAPI.getInstance().getMainDisplay().refresh();
    }
    
    @FXML
    void closeErrorMessageCallback(ActionEvent event)
    {
    	hideErrorMessageContainer();
    }

}
