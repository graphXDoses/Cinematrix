package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.FileNotFoundException;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.Users.Guest;
import com.texnologia_logismikou.Cinematrix.Views.LoginView;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class UserDashboardViewController
{

	@FXML private TabPane tabpane;
	
    @FXML
    void initialize()
    {
    	tabpane.getSelectionModel().select(0);
    }
    
    @FXML
    void signOutCallback(Event event)
    {
    	CinematrixAPI.getInstance().setCurrentUser(new Guest());
    	CinematrixAPI.ACCOUNT_CONTEXT.LOGIN_VIEW = new LoginView();
    	CinematrixAPI.getInstance()
    				.getActiveContext()
    				.promiseRedirectTo(CinematrixAPI.ACCOUNT_CONTEXT.LOGIN_VIEW);
    	CinematrixAPI.getInstance().getMainDisplay().refresh();
    }

}
