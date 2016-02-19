package edu.mum.bank.model;

import java.time.LocalDate;

import edu.mum.framework.Entry;
import edu.mum.framework.IAccount;
import edu.mum.framework.Organization;

public class DepositEntry extends Entry {

	public DepositEntry(double amount) {
		this.setAmount(amount);
		this.setDetail("Deposit");
		this.setDate(LocalDate.now().toString());
	}
	
	@Override
	public void compute(IAccount account) {
		double newBalance = account.getCurrentBalance() + this.getAmount();
		account.setCurrentBalance(newBalance);
	}

	@Override
    public boolean canSendMail(IAccount account) {
        if (account instanceof Organization) {
            return true;
        } else if (getAmount() >= 500.00) {
            return true;
        }
        return false;
    }
}
