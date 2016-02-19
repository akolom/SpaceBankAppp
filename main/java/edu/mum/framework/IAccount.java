package edu.mum.framework;

import java.util.List;

public interface IAccount {
	public Long getAccountNo();

	public void setAccountNo(Long accountNo);

	double getCurrentBalance();

	void setCurrentBalance(double newBalance);

	ICustomer getCustomer();

	void setCustomer(ICustomer customer);

	public List<IEntry> getEntries();
	
	public void setEntries(List<IEntry> entries);
	
	void notifyCustomer();

	double getInterestRate();

}
