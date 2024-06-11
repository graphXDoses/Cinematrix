package com.texnologia_logismikou.Cinematrix;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FooterBarController {

    @FXML
    private Label version_lbl;

    @FXML
    void initialize()
    {
    	PropertiesReader reader;
		try {
			reader = new PropertiesReader("properties-from-pom.properties");
			String version = reader.getProperty("AppVersion");
			version_lbl.setText(version);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
