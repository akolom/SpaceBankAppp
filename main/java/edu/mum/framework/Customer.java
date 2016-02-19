package edu.mum.framework;

public abstract class Customer implements ICustomer {

	private String name;
	private String street;
	private String state;
	private String zip;
	private String email; // is unique

	private String city;


	public Customer(String name, String street,
			String state, String city, String zip, String email) {
		super();
		this.name = name;
		this.street = street;
		this.state = state;
		this.city = city;
		this.zip = zip;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
