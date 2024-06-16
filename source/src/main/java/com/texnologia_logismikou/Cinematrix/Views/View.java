package com.texnologia_logismikou.Cinematrix.Views;

import java.io.IOException;

import com.texnologia_logismikou.Cinematrix.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class View<T> {

	private Parent root;
	private T controller;
	
	protected void loadFXML(String controller_name)
	{
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(App.class.getResource("screens/" + controller_name + ".fxml"));
		try {
			root = fxmlLoader.load();
			controller = fxmlLoader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("exports")
	public Parent getParent() { return(root); }
	public T getController() { return(controller); }
}
