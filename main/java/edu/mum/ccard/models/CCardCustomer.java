package edu.mum.ccard.models;


import edu.mum.framework.Person;

public class CCardCustomer extends Person {
	
	public CCardCustomer(String name, String street,
			String state, String city, String zip, String email) {
		
		super(name, street, state, city, zip, email, "0-0-0000");
		// TODO Auto-generated constructor 
	}
	
}
