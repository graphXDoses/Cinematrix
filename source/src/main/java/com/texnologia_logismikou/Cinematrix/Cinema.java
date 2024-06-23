package com.texnologia_logismikou.Cinematrix;

import com.texnologia_logismikou.Cinematrix.Managers.CinemaModal;

public class Cinema {
	
	private String name;
	private String address;
	private float  distance;
	private CinemaModal modal;
	
	public Cinema(String name, String address, float distance)
	{
		this.name = name;
		this.address = address;
		this.distance = distance;
		
		this.modal = new CinemaModal();
		this.modal.getController().setData(this);
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public float getDistance() {
		return distance;
	}

	public CinemaModal getModal() {
		return modal;
	}
}
