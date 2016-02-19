package edu.mum.framework.service;

import java.util.ArrayList;
import java.util.List;

import edu.mum.bank.model.DepositEntry;
import edu.mum.bank.model.WithdrawEntry;
import edu.mum.framework.IAccount;
import edu.mum.framework.IEntry;
import edu.mum.framework.Organization;

public class TransactionService extends FrameworkService {
	
	List<Observer> observers = new ArrayList<Observer>();
	
	
	
	public void addEntry(IAccount account, IEntry entry) {
		List<IEntry> entries = account.getEntries();
		account.getEntries().add(entry);
		entry.compute(account);
		db.addAccount(account); // on relational database it should be add entry
	}
	
	public List<IEntry> getEntries(IAccount account) {
		return account.getEntries();
	}


}
