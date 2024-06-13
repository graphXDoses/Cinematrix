package com.texnologia_logismikou.Cinematrix;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class Context implements IAppendable{
	
	private String name;
	private String icon_path;
	private List<View> views = new ArrayList<>();
	private ContextButton button = null;
	
	public Context(String name, String icon_path)
	{
		this.setName(name);
		this.setUrl(icon_path);
		button = new ContextButton(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return icon_path;
	}

	public void setUrl(String icon_path) {
		this.icon_path = icon_path;
	}

	@Override
	public void addTo(Node parent) {
		((HBox)parent).getChildren().add(this.button.getParent());
	}
}
