package edu.mum.ccard.models;

import edu.mum.framework.ICustomer;

public class BronzeAccount extends CCardAccount {

	public BronzeAccount(ICustomer customer, Long accountNo, String ccNumber, String expDate) {
		super(customer, accountNo, ccNumber, expDate);
	}

	@Override
	public double getMonthlyIntrestRate() {
		return 0.1;
	}

	@Override
	public double getMinimumPayment() {
		return 0.14;
	}

	@Override
	public double getInterestRate() {
		return 0.1;
	}

}
