package edu.mum.bank.model;

import edu.mum.framework.Account;
import edu.mum.framework.ICustomer;

public class SavingAccount extends Account {

	private final double interestRate = 0.1;
	
	public SavingAccount(ICustomer customer, Long accountNo) {
		super(customer, accountNo);
	}

	@Override
	public double getInterestRate() {
		return interestRate;
	}



}
