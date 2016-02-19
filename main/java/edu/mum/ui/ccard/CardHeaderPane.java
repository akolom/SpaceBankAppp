package edu.mum.ui.ccard;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import edu.mum.ccard.models.BronzeAccount;
import edu.mum.ccard.models.CCardAccount;
import edu.mum.ccard.models.CCardCustomer;
import edu.mum.ccard.models.GoldAccount;
import edu.mum.ccard.models.SilverAccount;
import edu.mum.framework.CommandManager;
import edu.mum.framework.IAccount;
import edu.mum.framework.ICustomer;
import edu.mum.framework.IEntry;
import edu.mum.framework.command.CreateAccountCommand;
import edu.mum.framework.command.ICommand;
import edu.mum.framework.service.AccountService;
import edu.mum.framework.service.TransactionService;
import edu.mum.framework.ui.HeaderPane;

public class CardHeaderPane implements HeaderPane {

    CardFrm cardFrm;
    AccountService accountService = new AccountService();
	TransactionService transactionService = new TransactionService();

    public CardHeaderPane(CardFrm cardFrm) {
        this.cardFrm = cardFrm;
    }

    @Override
    public void initialize() {
        try {
            addCardAccount();
            showTransactions();
            showMonthlyReport();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

	private void addCardAccount() throws IOException {
		cardFrm.JButton_NewCCAccount.addActionListener((event) -> addCreditCardAccountAction(event));

       // showAccountDialog(new BankCustomer(), "Add Personal Account");
	}

	private void showMonthlyReport() throws IOException {
		//showAccountDialog(new Company(), "Add Company Account");
		cardFrm.JButton_MonthlyReport.addActionListener((event) -> showMonthlyReportAction(event));

    }

	 private void showMonthlyReportAction(java.awt.event.ActionEvent event){
	    	try {
				String accnr = getTableValue(0);

				Long accountNo = Long.valueOf(accnr);
				IAccount account = accountService.getAccount(accountNo);

				List<IEntry> entries = transactionService.getEntries(account);
				
				edu.mum.ui.ccard.JDialog_MonthlyReport wd = new edu.mum.ui.ccard.JDialog_MonthlyReport(cardFrm, accnr, entries);
				wd.setBounds(430, 15, 575, 280);
				wd.show();
			} catch (Exception e) {
				// do nothing
			}
	    }
	private void showTransactions() throws IOException {
		//showAccountDialog(new Company(), "Add Company Account");
		cardFrm.JButton_Transactions.addActionListener((event) -> showTransactionAction(event));

    }
	
	private void addCreditCardAccountAction(java.awt.event.ActionEvent event) {
		/*
		 * JDialog_AddPAcc type object is for adding personal information
		 * construct a JDialog_AddPAcc type object set the boundaries and show
		 * it
		 */

		JDialog_AddCCAccount ccac = new JDialog_AddCCAccount(cardFrm);
		ccac.setBounds(450, 20, 300, 400);
		ccac.show();

		if (cardFrm.hitSubmit) {
			CCardCustomer customer = new CCardCustomer(cardFrm.clientName,
					cardFrm.street, cardFrm.state, cardFrm.city,
					cardFrm.zip, cardFrm.email);

			IAccount account = null;

			switch (cardFrm.accountType) {
				case "Gold":
					account = new GoldAccount(customer, Long.valueOf(cardFrm.accountNO), cardFrm.ccnumber, cardFrm.expdate);
					break;

				case "Bronze":
					account = new BronzeAccount(customer, Long.valueOf(cardFrm.accountNO), cardFrm.ccnumber, cardFrm.expdate);
					break;

				case "Silver":
					account = new SilverAccount(customer, Long.valueOf(cardFrm.accountNO), cardFrm.ccnumber, cardFrm.expdate);
					break;
			}

			ICommand createAccountCommand = new CreateAccountCommand(account);
			new CommandManager(createAccountCommand).submit();

			cardFrm.redrawTable();
			cardFrm.hitSubmit = false;
		}

	}

	private void showTransactionAction(ActionEvent event) {
		try {
			String accnr = getTableValue(0);

			Long accountNo = Long.valueOf(accnr);
			IAccount account = accountService.getAccount(accountNo);

			List<IEntry> entries = transactionService.getEntries(account);
			JDialog_Transaction wd = new JDialog_Transaction(cardFrm, accnr, entries);
			wd.setBounds(430, 15, 575, 280);
			wd.show();
		} catch (Exception e) {
			// do nothing
		}
	}


	

	private String getTableValue(int index) {
		int selection = cardFrm.JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String value = (String) cardFrm.model.getValueAt(selection, index);
			return value;
		} else {
			JOptionPane.showMessageDialog(cardFrm.JButton_ErrorMessage, "Select an account to perform an operation",
					"Select a row", JOptionPane.WARNING_MESSAGE);
			throw new RuntimeException("Select a row to perform an operation");
		}
	}

}
