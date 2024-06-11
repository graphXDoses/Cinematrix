package com.texnologia_logismikou.Cinematrix;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;

public class DisplayAreaController {
	
	@FXML
    private ScrollPane scroller;
	
	@FXML
	void initialize()
	{		
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("screens/AllMoviesView.fxml"));
		try {
			Parent root = fxmlLoader.load();
			scroller.getStyleClass().clear();
			scroller.getStyleClass().add("crank");
			scroller.setContent(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
