package com.texnologia_logismikou.Cinematrix;

public class Context {
	
	private String name;
	private String icon_path;
	
	public Context(String name, String icon_path)
	{
		this.setName(name);
		this.setUrl(icon_path);
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
}
