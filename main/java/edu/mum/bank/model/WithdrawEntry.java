package edu.mum.bank.model;

import java.time.LocalDate;

import edu.mum.framework.Entry;
import edu.mum.framework.IAccount;
import edu.mum.framework.Organization;

public class WithdrawEntry extends Entry {

	public WithdrawEntry(double amount) {
		this.setAmount(amount);
		this.setDetail("Withdrawn");
		this.setDate(LocalDate.now().toString());
	}
	
	@Override
	public void compute(IAccount account) {
		double newBalance = account.getCurrentBalance() - this.getAmount();
		account.setCurrentBalance(newBalance);
	}
	
	@Override
    public boolean canSendMail(IAccount account) {
        if (account instanceof Organization) {
            return true;
        } else if (account.getCurrentBalance() < 0) {
            return true;
        }
        return false;
    }
}
