package edu.mum.ui.bank;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import edu.mum.bank.model.CheckingAccount;
import edu.mum.bank.model.SavingAccount;
import edu.mum.framework.CommandManager;
import edu.mum.framework.IAccount;
import edu.mum.framework.ICustomer;
import edu.mum.framework.Organization;
import edu.mum.framework.Person;
import edu.mum.framework.command.CreateAccountCommand;
import edu.mum.framework.command.ICommand;
import edu.mum.framework.service.AccountService;
import edu.mum.framework.service.TransactionService;
import edu.mum.framework.ui.HeaderPane;

public class BankHeaderPane implements HeaderPane {

	BankFrm bankFrm;
	AccountService accountService = new AccountService();

	public BankHeaderPane(BankFrm bankFrm) {
		this.bankFrm = bankFrm;
	}

	@Override
	public void initialize() {
		bankFrm.JButton_PerAC.addActionListener((event) -> addPersonalAccountAction(event));
		bankFrm.JButton_CompAC.addActionListener((event) -> addCompanyAccountAction(event));
		bankFrm.JButton_Addinterest.addActionListener((event) -> addInterestAction(event));
	}

	void addPersonalAccountAction(ActionEvent event) {
		/*
		 * JDialog_AddPAcc type object is for adding personal information
		 * construct a JDialog_AddPAcc type object set the boundaries and show
		 * it
		 */

		JDialog_AddPAcc pac = new JDialog_AddPAcc(bankFrm);
		pac.setBounds(450, 20, 300, 400);
		pac.show();

		if (bankFrm.hitSubmit) {
			Person customer = new Person(bankFrm.clientName, bankFrm.city, bankFrm.state, bankFrm.street, bankFrm.zip,
					bankFrm.email, bankFrm.dob);

			IAccount account;
			if (bankFrm.accountType.equals("S")) {
				account = new SavingAccount(customer, Long.valueOf(bankFrm.accountnr));
			} else {
				account = new CheckingAccount(customer, Long.valueOf(bankFrm.accountnr));
			}

			ICommand createAccountCommand = new CreateAccountCommand(account);
			new CommandManager(createAccountCommand).submit();

			bankFrm.redrawTable();
			bankFrm.hitSubmit = false;
		}

	}

	void addCompanyAccountAction(ActionEvent event) {
		/*
		 * construct a JDialog_AddCompAcc type object set the boundaries and
		 * show it
		 */

		JDialog_AddCompAcc pac = new JDialog_AddCompAcc(bankFrm);
		pac.setBounds(450, 20, 300, 400);
		pac.show();

		if (bankFrm.hitSubmit) {
			// add row to table
			Organization customer = new Organization(bankFrm.clientName, bankFrm.city, bankFrm.state, bankFrm.street,
					bankFrm.zip, bankFrm.email, Integer.valueOf(bankFrm.no_of_employees));

			IAccount account;
			if (bankFrm.accountType.equals("S")) {
				account = new SavingAccount(customer, Long.valueOf(bankFrm.accountnr));
			} else {
				account = new CheckingAccount(customer, Long.valueOf(bankFrm.accountnr));
			}

			ICommand createAccountCommand = new CreateAccountCommand(account);
			new CommandManager(createAccountCommand).submit();

			bankFrm.redrawTable();
			bankFrm.hitSubmit = false;
		}

	}

	void addInterestAction(ActionEvent event) {
		JOptionPane.showMessageDialog(bankFrm.JButton_Addinterest, "Add interest to all accounts",
				"Add interest to all accounts", JOptionPane.WARNING_MESSAGE);

		// add interest
		accountService.addInterest();

		bankFrm.redrawTable();
	}

}
