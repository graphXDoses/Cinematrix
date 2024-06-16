package com.texnologia_logismikou.Cinematrix.Managers;

import java.io.IOException;

import com.texnologia_logismikou.Cinematrix.App;
import com.texnologia_logismikou.Cinematrix.Controllers.ContextButtonContainerController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

public class ContextButtonContainer {
	
	private Parent root;
	
	public ContextButtonContainer()
	{
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(App.class.getResource("screens/ContextButtonContainer.fxml"));
		try {
			root = fxmlLoader.load();
			ContextButtonContainerController controller = fxmlLoader.getController();
			
			controller.addContextButton(new ContextButton());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("exports")
	public Parent getParent() { return(root); }
	
}
