package edu.mum.ui.bank;

import edu.mum.framework.ui.*;
import javafx.scene.Node;

public class BankGUIFactory implements IGUIFactory {
    BankFrm bankFrm;
    public BankGUIFactory(BankFrm bankFrm) {
        this.bankFrm = bankFrm;
    }

    @Override
	public HeaderPane getHeaderPane() {
		return new BankHeaderPane(bankFrm);
	}
	
	@Override
	public ContentPane getContentPane() {
		return new BankContentPane(bankFrm);
	}
	
	@Override
	public RightPane getRightPane() {
		return new BankRightPane(bankFrm);
	}
	
	
}
