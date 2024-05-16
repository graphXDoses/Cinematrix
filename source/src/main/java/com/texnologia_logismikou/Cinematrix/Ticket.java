package com.texnologia_logismikou.Cinematrix;

public class Ticket {
	
	private String code;
	private String Customer;
	private double price;
	private String seat;
	
	public Ticket(String code, String customer, double price, String seat) {
		super();
		this.code = code;
		Customer = customer;
		this.price = price;
		this.seat = seat;
	}
	
	public void showInfo() {
		System.out.println("Code of the ticket: " + code);
		System.out.println("Name: " + Customer);
		System.out.println("Price: " + price);
		System.out.println("Seat: " + seat);
	}
}
