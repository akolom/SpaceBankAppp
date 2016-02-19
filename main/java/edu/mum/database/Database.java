package edu.mum.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mum.framework.IAccount;
import edu.mum.framework.ICustomer;

public class Database {
	
	public static Database INSTANCE = new Database();

	private Map<Long, IAccount> accountTbl = new HashMap<Long, IAccount>();
	
	// making database singleton pattern
	private Database() {
		
		
	}
	
	public void addAccount(IAccount account) {
		accountTbl.put(account.getAccountNo(), account);
	}
	
	public Map<Long, IAccount> getAllAccounts() {
		return accountTbl;
	}
	
	public IAccount getAccount(Long accountNo) {
		if(accountTbl.containsKey(accountNo)) {
			return accountTbl.get(accountNo);
		}
		return null;
	}
	
}
