package com.texnologia_logismikou.Cinematrix.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class DisplayAreaController {
	
	@FXML private ScrollPane scroller;
	
	@FXML
	void initialize()
	{
		scroller.getStyleClass().clear();
		scroller.getStyleClass().add(".scroll-bar");
	}
	
	public void setContent(Node content)
	{
		scroller.setVvalue(0);
		scroller.setContent(null);
		scroller.setContent(content);
	}
}
