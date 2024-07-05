package com.texnologia_logismikou.Cinematrix.Views;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.App;
import com.texnologia_logismikou.Cinematrix.CinematrixAPI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public abstract class View<T> {

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
	
//	public abstract void prepare() throws ClassNotFoundException;
	
	public void prepare() throws ClassNotFoundException
	{
		List<Method> methods = Arrays.asList(getClass().getMethods())
				  .stream()
				  .filter(m -> 
				  	m.getName()
				  	 .endsWith(CinematrixAPI.getInstance().getCurrentUser().getClass().getSimpleName())
		  	).toList();
			
			if(methods.isEmpty())
				throw new ClassNotFoundException();
			else
				for(Method m : methods)
				{				
					try {
						m.invoke(this);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	}
	
	@SuppressWarnings("exports")
	public Parent getParent() { return(root); }
	public T getController() { return(controller); }
}
