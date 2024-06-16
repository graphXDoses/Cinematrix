package com.texnologia_logismikou.Cinematrix.Managers;

import java.io.IOException;

import com.texnologia_logismikou.Cinematrix.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ContextButton extends Manager
{
	
	public ContextButton()
	{
		/*
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(App.class.getResource("screens/ContextButton.fxml"));
		try {
			root = fxmlLoader.load();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		super.loadFXML("ContextButton");
	}
	
}
