package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.CinematrixAPI;
import com.texnologia_logismikou.Cinematrix.PropertiesReader;
import com.texnologia_logismikou.Cinematrix.Users.UserCore;

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
    	UserCore user = CinematrixAPI.getInstance().getCurrentUser();
    	user_privilages_label.setText("[ " + CinematrixAPI.getInstance().getCurrentUser() + " ]");
    	user_privilages_label.setStyle("-fx-text-fill: -fx-act-usr-" + user.getClass().getSimpleName().toUpperCase());
    }
}
