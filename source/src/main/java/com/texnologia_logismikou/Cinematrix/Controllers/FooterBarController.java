package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.PropertiesReader;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FooterBarController {

    @FXML private Label version_label;
    @FXML private Label user_privilages_label;
    
    @FXML
    void initialize()
    {
		version_label.setText(PropertiesReader.getProperty("AppVersion"));
    }
    
    public void setData()
    {
//    	System.out.println(CinematrixAPI.getInstance().getCurrentUser());
    	user_privilages_label.setText("[ " + CinematrixAPI.getInstance().getCurrentUser() + " ]");
    }
}
