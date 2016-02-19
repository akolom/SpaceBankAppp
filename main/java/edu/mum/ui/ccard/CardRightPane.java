package edu.mum.ui.ccard;

import edu.mum.bank.model.DepositEntry;
import edu.mum.bank.model.WithdrawEntry;
import edu.mum.ccard.models.BronzeAccount;
import edu.mum.ccard.models.CCardAccount;
import edu.mum.ccard.models.CCardCustomer;
import edu.mum.ccard.models.ChargeEntry;
import edu.mum.ccard.models.GoldAccount;
import edu.mum.ccard.models.SilverAccount;
import edu.mum.framework.CommandManager;
import edu.mum.framework.IAccount;
import edu.mum.framework.ICustomer;
import edu.mum.framework.IEntry;
import edu.mum.framework.command.ICommand;
import edu.mum.framework.command.TransactionCommand;
import edu.mum.framework.service.AccountService;
import edu.mum.framework.service.TransactionService;
import edu.mum.framework.ui.RightPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class CardRightPane implements RightPane {
	CardFrm cardFrm;
	AccountService accountService = new AccountService();
	TransactionService transactionService = new TransactionService();

	public CardRightPane(CardFrm cardFrm) {
		this.cardFrm = cardFrm;
	}

	@Override
	public void initialize() {
		try {
			addInterest();
			charge();
			deposit();
			showTransaction();
			exit();
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	private void addInterest() {
		cardFrm.JButton_AddInterest.addActionListener((event) -> addCardInterest(event));

	}

	private void charge() {
		cardFrm.JButton_Charge.addActionListener((event) -> chargeAction(event));

	}

	private void exit() {
		cardFrm.JButton_Exit.addActionListener((event) -> exitAction(event));

	}

	private void deposit() throws IOException {
		cardFrm.JButton_Deposit.addActionListener((event) -> depoistAction(event));

	}

	private void showTransaction() {
		cardFrm.JButton_Transactions.addActionListener((event) -> showTransactionAction(event));
	}

	private void addCardInterest(java.awt.event.ActionEvent event) {
		JOptionPane.showMessageDialog(cardFrm.JButton_AddInterest, "Add interest to all accounts",
				"Add interest to all accounts", JOptionPane.WARNING_MESSAGE);
		accountService.addInterest();
		
		cardFrm.redrawTable();
	}

	void exitAction(java.awt.event.ActionEvent event) {
		System.exit(0);
	}

	private void chargeAction(java.awt.event.ActionEvent event) {
		try {
			String accnr = getTableValue(0);

			// Show the dialog for adding withdraw amount for the current mane
			edu.mum.ui.ccard.JDialog_Withdraw wd = new edu.mum.ui.ccard.JDialog_Withdraw(cardFrm, accnr);
			wd.setBounds(430, 15, 300, 200);
			wd.show();

			if (cardFrm.hitSubmit) {
				// compute new balance
				Long accNo = Long.valueOf(cardFrm.accountNO);
				Long chargeAmount = Long.parseLong(cardFrm.amountDeposit);
				IAccount account = accountService.getAccount(accNo);

				IEntry charge = new ChargeEntry(chargeAmount);
				ICommand chargeCommand = new TransactionCommand(account, charge);
				new CommandManager(chargeCommand).submit();

				double newAmount = account.getCurrentBalance();
				cardFrm.redrawTable();

				if (newAmount < 0) {
					JOptionPane.showMessageDialog(cardFrm.JButton_Charge,
							" Account " + accnr + " : balance is negative: $" + String.valueOf(newAmount) + " !",
							"Warning: negative balance", JOptionPane.WARNING_MESSAGE);
				}
				cardFrm.hitSubmit = false;
			}

		} catch (Exception e) {
			// do nothing
		}
	}

	private void depoistAction(java.awt.event.ActionEvent event) {
		try {
			String accNo = getTableValue(0);

			// Show the dialog for adding deposit amount for the current mane
			edu.mum.ui.ccard.JDialog_Deposit dep = new edu.mum.ui.ccard.JDialog_Deposit(cardFrm, accNo);
			dep.setBounds(430, 15, 300, 200);
			dep.show();

			// compute new balance
			if (cardFrm.hitSubmit) {
				Long accountNo = Long.valueOf(accNo);
				Long depositAmount = Long.parseLong(cardFrm.amountDeposit);
				IAccount account = accountService.getAccount(accountNo);

				IEntry depositEntry = new DepositEntry(depositAmount);
				ICommand depositCommand = new TransactionCommand(account, depositEntry);
				new CommandManager(depositCommand).submit();

				cardFrm.redrawTable();
				cardFrm.hitSubmit = false;
			}
		} catch (Exception e) {
			// do nothing
		}
	}

	private void showTransactionAction(ActionEvent event) {
		try {
			String accnr = getTableValue(0);

			Long accountNo = Long.valueOf(accnr);
			IAccount account = accountService.getAccount(accountNo);

			List<IEntry> entries = transactionService.getEntries(account);
			edu.mum.ui.ccard.JDialog_Transaction wd = new edu.mum.ui.ccard.JDialog_Transaction(cardFrm, accnr, entries);
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
