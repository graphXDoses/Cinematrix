package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.FileNotFoundException;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Users.User;
import com.texnologia_logismikou.Cinematrix.Views.ForgotPasswordView;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class LoginViewController
{
	
	@FXML private TextField email_inputfield;
	@FXML private Label email_label;
	@FXML private Pane email_label_obs;
	@FXML private PasswordField pass_inputfield;
	@FXML private Label pass_label;
	@FXML private Pane pass_label_obs;
	@FXML private HBox forgot_password_link;
	
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
		if(CinematrixAPI.getInstance().userAuthenticate(email_inputfield.getText(), pass_inputfield.getText()))
    	{
    		CinematrixAPI.getInstance().setCurrentUser(new User());
    		
    		CinematrixAPI.ACCOUNT_CONTEXT.USER_DASHBOARD_VIEW = new UserDashboardView();
    		CinematrixAPI.getInstance()
    					.getActiveContext()
    					.promiseRedirectTo(CinematrixAPI.ACCOUNT_CONTEXT.USER_DASHBOARD_VIEW);
    		CinematrixAPI.getInstance().getMainDisplay().refresh();
    	}
    	else
    		System.out.println("Failed to authenticate user.");
    }
    
    @FXML
    void switchToSignupCallback(ActionEvent event)
    {
    	CinematrixAPI.ACCOUNT_CONTEXT.SIGNUP_VIEW = new SignUpView();
		CinematrixAPI.getInstance()
    				.getActiveContext()
    				.promiseRedirectTo(CinematrixAPI.ACCOUNT_CONTEXT.SIGNUP_VIEW);
    	CinematrixAPI.getInstance().getMainDisplay().refresh();
    }
    
    @FXML
    void forgotPasswordCallback(ActionEvent event)
    {	
    	CinematrixAPI.ACCOUNT_CONTEXT.FORGOT_PASSWORD_VIEW = new ForgotPasswordView();
    	CinematrixAPI.getInstance()
    				.getActiveContext()
    				.promiseRedirectTo(CinematrixAPI.ACCOUNT_CONTEXT.FORGOT_PASSWORD_VIEW);
    	CinematrixAPI.getInstance().getMainDisplay().refresh();
    }

}
