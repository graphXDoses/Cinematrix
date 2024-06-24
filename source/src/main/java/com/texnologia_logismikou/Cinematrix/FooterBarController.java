package com.texnologia_logismikou.Cinematrix;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FooterBarController {

    @FXML
    private Label version_lbl;

    private String github_repo_link = null;
    
    @FXML
    void initialize()
    {
    	PropertiesReader reader;
		try {
			reader = new PropertiesReader("properties-from-pom.properties");
			
			String version = reader.getProperty("AppVersion");
			version_lbl.setText(version);
			github_repo_link = reader.getProperty("RepoLink");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void gotoGithubRepo()
    {
    	try {
			Desktop.getDesktop().browse(new URI(github_repo_link));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
