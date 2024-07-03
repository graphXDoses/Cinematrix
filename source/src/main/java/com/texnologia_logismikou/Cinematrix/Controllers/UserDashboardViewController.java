package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.FileNotFoundException;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Users.Guest;
import com.texnologia_logismikou.Cinematrix.Users.User;
import com.texnologia_logismikou.Cinematrix.Views.LoginView;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

public class UserDashboardViewController
{

	@FXML private TabPane tabpane;
	@FXML private Label account_create_date;
	@FXML private Label user_name_label;
	
    @FXML
    void initialize()
    {
    	tabpane.getSelectionModel().select(0);
    }
    
    public void setData()
    {
    	User user = (User)CinematrixAPI.getInstance().getCurrentUser();
    	user_name_label.setText(user.getUserFields().getName().getStringValue());
    	account_create_date.setText(user.getAccountCreationDate());
    }
    
    @FXML
    void logOutCallback(Event event)
    {
    	CinematrixAPI.getInstance().setCurrentUser(new Guest());
    	CinematrixAPI.ACCOUNT_CONTEXT.LOGIN_VIEW = new LoginView();
    	CinematrixAPI.getInstance()
    				.getActiveContext()
    				.promiseRedirectTo(CinematrixAPI.ACCOUNT_CONTEXT.LOGIN_VIEW);
    	CinematrixAPI.getInstance().getMainDisplay().refresh();
    }

}
