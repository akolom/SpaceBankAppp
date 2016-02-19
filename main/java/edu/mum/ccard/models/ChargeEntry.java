package edu.mum.ccard.models;

import java.time.LocalDate;

import edu.mum.framework.Entry;
import edu.mum.framework.IAccount;
import edu.mum.framework.Organization;

public class ChargeEntry extends Entry {
	
	public ChargeEntry(double amount) {
		this.setAmount(amount);
		this.setDetail("Charge");
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
        } else if (this.getAmount() > 400 || (account.getCurrentBalance()-this.getAmount()<0)) {
            return true;
        }
		return false;
	}

}
