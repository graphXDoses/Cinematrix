package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.FileNotFoundException;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.SignInException;
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
import javafx.scene.layout.Pane;

public class LoginViewController
{
	
	@FXML private TextField email_inputfield;
	@FXML private Label email_label;
	@FXML private Pane email_label_obs;
	@FXML private PasswordField pass_inputfield;
	@FXML private Label pass_label;
	@FXML private Pane pass_label_obs;
	
	@FXML private Button login_button;
	
	private int YOffset = 20;

    @FXML
    void initialize()
    {
    	toggleActiveHighlighted(email_inputfield, email_label, email_label_obs, false);
    	toggleActiveHighlighted(pass_inputfield, pass_label, pass_label_obs, false);
    	
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
    void loginCallback(ActionEvent event)
    {
    	UserDashboardView view = (UserDashboardView)CinematrixAPI.getInstance().getActiveContext().getViews().get(2);
    	
    	/*
    	 * 	Add a text box that shows the error message to the user.
    	 * 	We could also clear the fields so that the users can retry immediately.
    	 */
    	
    	try {
			CinematrixAPI.getInstance().userSignIn(email_inputfield.getText(), pass_inputfield.getText());
		} catch (SignInException e) {
			// e.printStackTrace();
			switch(e.getMessage()) {
			case "INVALID_LOGIN_CREDENTIALS": System.out.println("Email or Password are incorrect!"); break;
			case "MISSING_PASSWORD": System.out.println("Password cannot be empty!"); break;
			case "INVALID_EMAIL": System.out.println("Please provide a valid email address."); break;
			default: System.out.println("Internal error occured. Please try again!");
			}
			return;
		}
    	
    	try {
			CinematrixAPI.getInstance().getActiveContext().goToView(view);
			CinematrixAPI.getInstance().getMainDisplay().refresh();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void switchToSignupCallback(ActionEvent event)
    {
    	SignUpView view = (SignUpView)CinematrixAPI.getInstance().getActiveContext().getViews().get(1);
    	
    	try {
			CinematrixAPI.getInstance().getActiveContext().goToView(view);
			CinematrixAPI.getInstance().getMainDisplay().refresh();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
