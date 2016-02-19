package edu.mum.framework.command;

import edu.mum.framework.IAccount;
import edu.mum.framework.IEntry;
import edu.mum.framework.service.EmailNotificationObserver;
import edu.mum.framework.service.NotificationServicePublisher;
import edu.mum.framework.service.Observer;
import edu.mum.framework.service.TransactionService;

public class TransactionCommand implements ICommand {
	
	IEntry entry;
	IAccount account;
	TransactionService transactionService;
	
	NotificationServicePublisher notificationService = new NotificationServicePublisher();
	Observer observer = new EmailNotificationObserver(notificationService);
	
	public TransactionCommand(IAccount account, IEntry entry) {
		this.account = account;
		this.entry = entry;
		transactionService = new TransactionService();
	}

	@Override
	public void execute() {
		transactionService.addEntry(account, entry);
		
		if (entry.canSendMail(account)) {
			notificationService.notifyObservers(account, entry);
        }
	}

}
