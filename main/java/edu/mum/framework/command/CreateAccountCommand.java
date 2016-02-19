package edu.mum.framework.command;

import edu.mum.framework.IAccount;
import edu.mum.framework.service.AccountService;

public class CreateAccountCommand implements ICommand {

	private IAccount account;
	private AccountService accountService;
	
	public CreateAccountCommand(IAccount account) {
		this.account = account;
		accountService = new AccountService();
	}
	
	@Override
	public void execute() {
		accountService.addAccount(account);
	}

}
