package edu.mum.ui.bank;

import edu.mum.framework.ui.ContentPane;

public class BankContentPane implements ContentPane {

    BankFrm bankFrm;

    @Override
    public void initialize() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


	public BankContentPane(BankFrm bankFrm) {
        this.bankFrm = bankFrm;

	}



	public void update() {
	}
}
