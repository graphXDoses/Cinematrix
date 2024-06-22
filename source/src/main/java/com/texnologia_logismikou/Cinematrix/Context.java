package com.texnologia_logismikou.Cinematrix;

import java.util.ArrayList;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.Managers.ContextButton;
import com.texnologia_logismikou.Cinematrix.Views.View;

public class Context{
	
	private String name;
	private String icon_path;
	private List<View> views = new ArrayList<>();
	

	private ContextButton button = null;
	
	public Context(String name, String icon_path, View ...input_views )
	{
		this.setName(name);
		this.setUrl(icon_path);
		button = new ContextButton(this);
		
		for(View v : input_views)
		{
			views.add(v);
		}
	}

	public String getName() {
		return name;
	}
	
	public List<View> getViews() {
		return views;
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
	
	public ContextButton getButton()
	{
		return(button);
	}
}
