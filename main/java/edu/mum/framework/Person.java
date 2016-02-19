package edu.mum.framework;

public class Person extends Customer implements IPerson {
	private String dob;
	public Person(String name2, String street2, String state2, String city2, String zip2, String email2, String dop) {
		super(name2, street2, state2, city2, zip2, email2);
		this.dob = dob;
	}



	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

}
