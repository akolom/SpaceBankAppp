package edu.mum.ccard.models;

import edu.mum.framework.ICustomer;

public class GoldAccount extends CCardAccount {

	public GoldAccount(ICustomer customer, Long accountNo, String ccNumber, String expDate) {
		super(customer, accountNo, ccNumber, expDate);
	}

	@Override
	public double getMonthlyIntrestRate() {
		return 0.06;
	}

	@Override
	public double getMinimumPayment() {
		return 0.1;
	}

	@Override
	public double getInterestRate() {
		return 0.06;
	}

}
