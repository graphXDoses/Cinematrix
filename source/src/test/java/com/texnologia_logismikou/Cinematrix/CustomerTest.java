package com.texnologia_logismikou.Cinematrix;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CustomerTest {

	//Test method for Customer.getFull_Name()
	//Since Customer.email and Customer.phone_number are both uninitialized strings, there is no reason to 
	//create their tests since they would be the exact same as this one.
	@Test
	void getFullNameUninitialized() {
		
		Customer tempCustomer = new Customer();
		String customerName = tempCustomer.getFull_name();
		
		assertNotNull(customerName, "The customer's name is returned, from the getter method, as null!");
	}
	
	//Test method for Customer.setFull_name()
	//Again this is an issue with the values that the setter gets, not being checked.
	//It can only receive Strings but there is a chance the String they receive is null.
	//The other setters should follow the same example.
	@Test
	void setFullNameAsNull() {
		
		Customer tempCustomer = new Customer();
		String customerName = null;
		tempCustomer.setFull_name(customerName);
		
		assertNotNull(tempCustomer.getFull_name(), "The customer's name is set, with the setter method, as null!");
	}
	
	//How can the code pass the Unit tests?
	//For the Unit tests to pass, the methods used must produce the expected result.
	//setFullName(): The given argument must never be null. If it is, then an empty String or some other default value
	//should be set.
	//getFullName(): If the attribute that will be returned is null then an empty String or some other default value 
	//should be returned.
	//The same applies for all getter/setter methods of type String.
}
