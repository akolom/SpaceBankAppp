package edu.mum.framework.service;

import edu.mum.framework.IAccount;
import edu.mum.framework.IEntry;

public interface Observer {

	void transactionNotification(IAccount account, IEntry entry);
}
