package edu.mum.framework.service;

import edu.mum.framework.IAccount;
import edu.mum.framework.IEntry;

public interface Publisher {
	
	void registerObserver(Observer o);
	void removeObserver(Observer o);
	void notifyObservers(IAccount account, IEntry entry);

}
