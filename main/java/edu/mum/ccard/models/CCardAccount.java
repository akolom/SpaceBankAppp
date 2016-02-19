package edu.mum.ccard.models;

import edu.mum.framework.*;

public abstract class CCardAccount extends Account {

	private String CCNumber;
	private String ExpDate;

	public CCardAccount(ICustomer customer, Long accountNo, String CCNumber, String expDate) {
		super(customer, accountNo);
		this.CCNumber = CCNumber;
		this.ExpDate = expDate;
		// TODO Auto-generated constructor stub
	}

	//@Override
	//public void notifyCustomer() {
	//	//Get last entry
	//	IEntry entry = this.getAllEntries().get(this.getAllEntries().size()-1);
	//	this.getCustomer().onTransactionChange(this,entry);
	//}

	public String getCCNumber() {
		return CCNumber;
	}

	public void setCCNumber(String cCNumber) {
		CCNumber = cCNumber;
	}

	public String getExpDate() {
		return ExpDate;
	}

	public void setExpDate(String expDate) {
		ExpDate = expDate;
	}

	public abstract double getMonthlyIntrestRate();
	public abstract double getMinimumPayment();
}
