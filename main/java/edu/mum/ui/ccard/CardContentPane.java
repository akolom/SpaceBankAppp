package edu.mum.ui.ccard;

import edu.mum.framework.ui.ContentPane;

public class CardContentPane implements ContentPane {

    CardFrm cardFrm;

    @Override
    public void initialize() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


	public CardContentPane(CardFrm cardFrm) {
        this.cardFrm = cardFrm;

	}



	public void update() {
	}
}
