package edu.mum.framework.service;

import edu.mum.framework.IAccount;
import edu.mum.framework.IEntry;

public class EmailNotificationObserver implements Observer {

	public EmailNotificationObserver(NotificationServicePublisher notificationService) {
		notificationService.registerObserver(this);
	}
	
	@Override
	public void transactionNotification(IAccount account, IEntry entry) {
		sendEmail(account, entry);
	}

	private void sendEmail(IAccount account, IEntry entry) {
		System.out.println("Sending email to " + account.getCustomer().getEmail());
		System.out.println("For transaction");
		System.out.println(entry);
	}
	
	
	
	

}
