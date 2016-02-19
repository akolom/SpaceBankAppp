package edu.mum.framework;

import java.util.LinkedList;
import java.util.List;

public abstract class Account implements IAccount {
	private Long accountNo;
	private double balance;
	private List<IEntry> entries;
	private ICustomer customer;
	
	public Account(ICustomer customer, Long accountNo) {
		this.customer = customer;
		this.setAccountNo(accountNo);
		this.balance = 0.0;
		entries = new LinkedList<IEntry>(); // using linked list to maintain the order
	}
	
	public double getCurrentBalance() {
		return balance;
	}
	
	public void setCurrentBalance(double balance) {
		this.balance = balance;
	}

	public ICustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ICustomer customer) {
		this.customer = customer;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	
	public List<IEntry> getEntries() {
		return entries;
	}
	
	public void setEntries(List<IEntry> entries) {
		this.entries = entries;
	}
	
	public void notifyCustomer() {
		// TODO - implement
	}
	
}
