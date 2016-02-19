package edu.mum.ui.bank;

import edu.mum.bank.model.CheckingAccount;
import edu.mum.bank.model.DepositEntry;
import edu.mum.bank.model.SavingAccount;
import edu.mum.bank.model.WithdrawEntry;
import edu.mum.framework.CommandManager;
import edu.mum.framework.IAccount;
import edu.mum.framework.ICustomer;
import edu.mum.framework.IEntry;
import edu.mum.framework.Organization;
import edu.mum.framework.Person;
import edu.mum.framework.command.ICommand;
import edu.mum.framework.command.TransactionCommand;
import edu.mum.framework.service.AccountService;
import edu.mum.framework.service.TransactionService;
import edu.mum.framework.ui.RightPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class BankRightPane implements RightPane {
	BankFrm bankFrm;
	AccountService accountService = new AccountService();
	TransactionService transactionService = new TransactionService();

	public BankRightPane(BankFrm bankFrm) {
		this.bankFrm = bankFrm;
	}

	@Override
	public void initialize() {
		try {
			withdraw();
			deposit();
			showTransaction();
			exit();
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	private void exit() {
		bankFrm.JButton_Exit.addActionListener((event) -> exitApplicationAction(event));

	}

	private void withdraw() throws IOException {
		// showTransaction(new Withdraw(), "Withdraw");
		bankFrm.JButton_Withdraw.addActionListener((event) -> withdrawAmountAction(event));

	}

	private void deposit() throws IOException {
		// showTransaction(new Deposit(), "Deposit");
		bankFrm.JButton_Deposit.addActionListener((event) -> depositeAmountAction(event));

	}

	private void showTransaction() {
		bankFrm.JButton_ShowTransaction.addActionListener((event) -> showTransactionAction(event));
	}

	void withdrawAmountAction(ActionEvent event) {
		try {
			String accnr = getTableValue(0);

			// Show the dialog for adding withdraw amount for the current mane
			JDialog_Withdraw wd = new JDialog_Withdraw(bankFrm, accnr);
			wd.setBounds(430, 15, 300, 200);
			wd.show();

			if (bankFrm.hitSubmit) {
				// compute new balance
				Long accountNo = Long.valueOf(bankFrm.accountnr);
				Long withdrawnAmount = Long.parseLong(bankFrm.amountDeposit);
				IAccount account = accountService.getAccount(accountNo);

				IEntry withdraw = new WithdrawEntry(withdrawnAmount);
				ICommand withdrawCommand = new TransactionCommand(account, withdraw);
				new CommandManager(withdrawCommand).submit();

				bankFrm.redrawTable();
				double newAmount = account.getCurrentBalance();
				if (newAmount < 0) {
					JOptionPane.showMessageDialog(bankFrm.JButton_Withdraw,
							" Account " + accnr + " : balance is negative: $" + String.valueOf(newAmount) + " !",
							"Warning: negative balance", JOptionPane.WARNING_MESSAGE);
				}

				bankFrm.hitSubmit = false;
			}
		} catch (Exception e) {
			// do nothing
		}

	}

	void depositeAmountAction(ActionEvent event) {
		try {
			String accnr = getTableValue(0);

			// Show the dialog for adding deposit amount for the current mane
			JDialog_Deposit dep = new JDialog_Deposit(bankFrm, accnr);
			dep.setBounds(430, 15, 300, 200);
			dep.show();

			if (bankFrm.hitSubmit) {
				// compute new balance
				Long accountNo = Long.valueOf(bankFrm.accountnr);
				Long depositAmount = Long.parseLong(bankFrm.amountDeposit);

				IAccount account = accountService.getAccount(accountNo);

				IEntry depositEntry = new DepositEntry(depositAmount);
				ICommand depositCommand = new TransactionCommand(account, depositEntry);
				new CommandManager(depositCommand).submit();

				bankFrm.redrawTable();
				bankFrm.hitSubmit = false;
			}
		} catch (Exception e) {
			// do nothing
		}

	}

	public void showTransactionAction(ActionEvent event) {
		try {
			String accnr = getTableValue(0);

			Long accountNo = Long.valueOf(accnr);
			IAccount account = accountService.getAccount(accountNo);

			List<IEntry> entries = transactionService.getEntries(account);

			JDialog_Transaction wd = new JDialog_Transaction(bankFrm, accnr, entries);
			wd.setBounds(430, 15, 575, 280);
			wd.show();
		} catch (Exception e) {
			// do nothing
		}
	}

	private String getTableValue(int index) {
		int selection = bankFrm.JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String value = (String) bankFrm.model.getValueAt(selection, index);
			return value;
		} else {
			JOptionPane.showMessageDialog(bankFrm.JButton_ErrorMessage, "Select an account to perform an operation",
					"Select a row", JOptionPane.WARNING_MESSAGE);
			throw new RuntimeException("Select a row to perform an operation");
		}

	}

	void exitApplicationAction(java.awt.event.ActionEvent event) {
		bankFrm.setVisible(false); // hide the Frame
		bankFrm.dispose(); // free the system resources
		System.exit(0); // close the application
	}

}
