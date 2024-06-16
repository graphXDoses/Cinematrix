package com.texnologia_logismikou.Cinematrix.Controllers;

import com.texnologia_logismikou.Cinematrix.PropertiesReader;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FooterBarController {

    @FXML private Label version_lbl;
    
    @FXML
    void initialize()
    {
		version_lbl.setText(PropertiesReader.getProperty("AppVersion"));
    }
}
