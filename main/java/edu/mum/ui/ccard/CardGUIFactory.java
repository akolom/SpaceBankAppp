package edu.mum.ui.ccard;

import edu.mum.framework.ui.ContentPane;
import edu.mum.framework.ui.HeaderPane;
import edu.mum.framework.ui.IGUIFactory;
import edu.mum.framework.ui.RightPane;

public class CardGUIFactory implements IGUIFactory {
    CardFrm cardFrm;
    public CardGUIFactory(CardFrm cardFrm) {
        this.cardFrm = cardFrm;
    }

    @Override
	public HeaderPane getHeaderPane() {
		return new CardHeaderPane(cardFrm);
	}
	
	@Override
	public ContentPane getContentPane() {
		return new CardContentPane(cardFrm);
	}
	
	@Override
	public RightPane getRightPane() {
		return new CardRightPane(cardFrm);
	}
}
