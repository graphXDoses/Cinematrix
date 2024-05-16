package com.texnologia_logismikou.Cinematrix;

public class Screening {

	private String MovieTitle;
	private int RoomNo;
	private String hour;
	private String date;
	
	public Screening(String movieTitle, int roomNo, String hour, String date) {
		super();
		MovieTitle = movieTitle;
		RoomNo = roomNo;
		this.hour = hour;
		this.date = date;
	}
	
	public void showDetails() {
		System.out.println("The title of the movie is: " + MovieTitle);
		System.out.println("Room: " + RoomNo);
		System.out.println("Hour: " + hour);
		System.out.println("Date: " + date);
	}

}
