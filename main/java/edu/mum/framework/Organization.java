package edu.mum.framework;

public class Organization extends Customer implements IOrganization {
	private int noOfEmployees;
	
	public Organization(String name, String street, 
			String state,  String city, String zip, String email,  int noOfEmployees ) {
		super(name, street, state, city, zip, email );
		this.noOfEmployees = noOfEmployees;
	
	}


	@Override
	public int getNoOfemployees() {
		return noOfEmployees;
	}

	@Override
	public void setNoOfemployees(int noOfemployees) {
		this.noOfEmployees = noOfemployees;
	}

	

	
	
	

}
