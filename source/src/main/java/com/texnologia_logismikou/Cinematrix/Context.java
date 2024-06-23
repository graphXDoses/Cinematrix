package com.texnologia_logismikou.Cinematrix;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.Managers.ContextButton;
import com.texnologia_logismikou.Cinematrix.Views.View;

public class Context{
	
	private String name;
	private String icon_path;
	private List<View> views = new ArrayList<>();
	private View activeView = null;
	private ContextButton button = null;
	
	public Context(String name, String icon_path, View ...input_views )
	{
		this.setName(name);
		this.setUrl(icon_path);
		button = new ContextButton(this);
		
		for(View v : input_views) { views.add(v); }
		
		try {
			goToView(input_views[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public ContextButton getButton() { return(button); }
	public View getActiveView() { return(activeView);	}
	
	public void goToView(View view) throws FileNotFoundException {
		if(views.indexOf(view) != -1)
			this.activeView = view;
		else
			throw new FileNotFoundException();
	}
}
