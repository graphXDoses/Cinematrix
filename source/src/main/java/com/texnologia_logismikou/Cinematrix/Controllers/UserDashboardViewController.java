package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.FileNotFoundException;

import com.texnologia_logismikou.Cinematrix.CinemaSystem;
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
    	LoginView view = (LoginView)CinemaSystem.getInstance().getActiveContext().getViews().get(0);
    	tabpane.getSelectionModel().select(0);
    	
    	try {
			CinemaSystem.getInstance().getActiveContext().goToView(view);
			CinemaSystem.getInstance().getMainDisplay().refresh();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
