package com.texnologia_logismikou.Cinematrix;

import java.util.ArrayList;

public class Room {
	private int no;
	private int capacity;
	private ArrayList<int[]> type = new ArrayList<>();
	private ArrayList<int[]> available_seats = new ArrayList<>();
	
	public void showDetails() {
		System.out.println("Your seat is: " + no);
		System.out.println("Capacity is: " + capacity);

	}
	
}
