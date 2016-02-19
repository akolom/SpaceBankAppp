package edu.mum.ccard.models;

import edu.mum.framework.ICustomer;

public class SilverAccount extends CCardAccount{

	public SilverAccount(ICustomer customer, Long accountNo, String ccNumber, String expDate) {
		super(customer, accountNo, ccNumber, expDate);
	}

	@Override
	public double getMonthlyIntrestRate() {
		return 0.08;
	}

	@Override
	public double getMinimumPayment() {
		return 0.12;
	}

	@Override
	public double getInterestRate() {
		return 0.08;
	}

}
