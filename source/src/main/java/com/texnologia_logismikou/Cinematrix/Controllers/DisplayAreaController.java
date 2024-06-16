package com.texnologia_logismikou.Cinematrix.Controllers;

import java.io.IOException;

import com.texnologia_logismikou.Cinematrix.App;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;

public class DisplayAreaController {
	
	@FXML
    private ScrollPane scroller;
	
	@FXML
	void initialize()
	{		
		/*
		FXMLLoader fxmlLoader = new FXMLLoader();
		
//		fxmlLoader.setLocation(App.class.getResource("screens/AllMoviesView.fxml"));
		fxmlLoader.setLocation(App.class.getResource("screens/MovieDetailsView.fxml"));
		
		try {
			Parent root = fxmlLoader.load();
			scroller.getStyleClass().clear();
			scroller.getStyleClass().add("crank");
			scroller.setContent(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		scroller.getStyleClass().clear();
		scroller.getStyleClass().add("crank");
	}
	
	public void setContent(Node content)
	{
		scroller.setContent(content);
	}
}
