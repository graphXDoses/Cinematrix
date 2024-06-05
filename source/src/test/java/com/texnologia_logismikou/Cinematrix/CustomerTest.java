package com.texnologia_logismikou.Cinematrix;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CustomerTest {

	//Test method for Customer.getFull_Name()
	//Since Customer.email and Customer.phone_number are both uninitialized strings, there is no reason to 
	//create their tests since they would be the exact same as this one.
	@Test
	void getFullNameTest() {
		
		Customer tempCustomer = new Customer();
		String customerName = tempCustomer.getFull_name();
		
		assertNotNull(customerName, "The customer's name is returned, from the getter method, as null!");
	}
	
	//Test method for Customer.setFull_name()
	//Again this is an issue with the values that the setter gets, not being checked.
	//It can only receive Strings but there is a chance the String they receive is null.
	//The other setters should follow the same example.
	@Test
	void setFullNameTest() {
		
		Customer tempCustomer = new Customer();
		String customerName = null;
		tempCustomer.setFull_name(customerName);
		
		assertNotNull(tempCustomer.getFull_name(), "The customer's name is set, with the setter method, as null!");
	}
}
