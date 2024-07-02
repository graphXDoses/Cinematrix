package com.texnologia_logismikou.Cinematrix.Contexts;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.texnologia_logismikou.Cinematrix.Managers.ContextButton;
import com.texnologia_logismikou.Cinematrix.Views.LoginView;
import com.texnologia_logismikou.Cinematrix.Views.View;

public class Context{
	
	private String name;
	private String icon_path;
//	private List<View> views = new ArrayList<>();
	private ContextButton button = null;
	
	protected View activeView = null;
	protected View defaultView;
	
	protected Context(String name, String icon_path)
	{
		this.setName(name);
		this.setUrl(icon_path);
		button = new ContextButton(this);
	}

	public String getName() {
		return name;
	}
	/*
	public List<View> getViews() {
		return views;
	}
	*/
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

	public void promiseRedirectTo(View view)
	{
		if(view == null)
			this.activeView = this.defaultView;
		else
		{
			this.activeView = view;
			if(view.getClass().equals(this.defaultView.getClass()))
				this.defaultView = view;
		}
	}
}
