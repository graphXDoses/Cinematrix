package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.FileNotFoundException;

import com.texnologia_logismikou.Cinematrix.CinemaSystem;
import com.texnologia_logismikou.Cinematrix.SignUpException;
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
	
	private int YOffset = 20;

    @FXML
    void initialize()
    {
    	toggleActiveHighlighted(name_inputfield, name_label, name_label_obs, false);
    	toggleActiveHighlighted(email_inputfield, email_label, email_label_obs, false);
    	toggleActiveHighlighted(pass_inputfield, pass_label, pass_label_obs, false);
    	
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
    	UserDashboardView view = (UserDashboardView)CinemaSystem.getInstance().getActiveContext().getViews().get(2);
    	
    	/*
    	 * 	For each error case instead of printing the message to the console create a small box to display the message to the user.
    	 * 	Also we can clear the fields (or the fields that produced the error) so that the user can re-enter them.
    	 */
    	try {
			CinemaSystem.getInstance().userSignUp(name_inputfield.getText(), email_inputfield.getText(), pass_inputfield.getText());
		} catch (SignUpException e) {
			// e.printStackTrace();
			switch(e.getMessage()) {
			case "INVALID_EMAIL": System.out.println("Please provide a valid email address."); break;
			case "EMPTY_NAME": System.out.println("The name cannot be empty!"); break;
			case "EMAIL_EXISTS": System.out.println("There is already an account with this email!"); break;
			case "MISSING_PASSWORD": System.out.println("A password is required for the creation of the account!"); break;
			case "WEAK_PASSWORD : Password should be at least 6 characters": System.out.println("The password must be at least 7 characters long!"); break;
			default: System.out.println("There was an error during sign up. Please try again!");
			}
			return;
		}
    	try {
			CinemaSystem.getInstance().getActiveContext().goToView(view);
			CinemaSystem.getInstance().getMainDisplay().refresh();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void switchToLoginCallback(ActionEvent event)
    {
    	LoginView view = (LoginView)CinemaSystem.getInstance().getActiveContext().getViews().get(0);
    	
    	try {
			CinemaSystem.getInstance().getActiveContext().goToView(view);
			CinemaSystem.getInstance().getMainDisplay().refresh();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
