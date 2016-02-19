package edu.mum.framework.service;

import java.util.ArrayList;
import java.util.List;

import edu.mum.framework.IAccount;

public class AccountService extends FrameworkService {
	
	public void addAccount(IAccount account) {
		db.addAccount(account);
	}
	
	public IAccount getAccount(Long accountNo) {
		return db.getAccount(accountNo);
	}
	
	public List<IAccount> getAllAccounts() {
		List<IAccount> accounts = new ArrayList<IAccount>(db.getAllAccounts().values());
		return accounts;
	}
	
	public void addInterest() {
		List<IAccount> accounts = getAllAccounts();
		accounts.stream().filter((a) -> a.getCurrentBalance() > 0)
		.forEach((account) -> {
			double newBalance = 
					account.getCurrentBalance() + 
					account.getCurrentBalance() * account.getInterestRate();
			account.setCurrentBalance(newBalance);
			db.addAccount(account);
		});
	}

}
