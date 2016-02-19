package edu.mum.framework.service;

import java.util.ArrayList;
import java.util.List;

import edu.mum.framework.IAccount;
import edu.mum.framework.IEntry;

public class NotificationServicePublisher implements Publisher {

	List<Observer> observers = new ArrayList<Observer>();

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
		
	}

	@Override
	public void notifyObservers(IAccount account, IEntry entry) {
		observers.forEach((o) -> o.transactionNotification(account, entry));
	}
}
