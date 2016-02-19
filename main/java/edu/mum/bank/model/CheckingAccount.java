package edu.mum.bank.model;

import edu.mum.framework.Account;
import edu.mum.framework.ICustomer;

public class CheckingAccount extends Account {

	private final double interestRate = 0.1;
	
	public CheckingAccount(ICustomer customer, Long accountNo) {
		super(customer, accountNo);
	}

	public double getInterestRate() {
		return interestRate;
	}
	

}
