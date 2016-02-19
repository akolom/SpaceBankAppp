package edu.mum.ui.ccard;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import edu.mum.bank.model.DepositEntry;
import edu.mum.bank.model.WithdrawEntry;
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
import edu.mum.framework.command.TransactionCommand;
import edu.mum.framework.service.AccountService;
import edu.mum.framework.service.TransactionService;
import edu.mum.framework.ui.FrameWorkInitializer;
import edu.mum.framework.ui.IGUIFactory;
import edu.mum.ui.ccard.JDialog_Transaction;

/**
 * A basic JFC based application.
 */
public class CardFrm extends javax.swing.JFrame implements FrameWorkInitializer {
	/****
	 * init variables in the object
	 ****/
	public String accountNO, clientName, street, city, zip, state, dob, email, accountType, amountDeposit, expdate,
			ccnumber;

	public boolean hitSubmit = false;
	public DefaultTableModel model;
	public JTable JTable1;
	public JScrollPane JScrollPane1;
	// CardFrm thisframe;
	public Object rowdata[];

	javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
	javax.swing.JButton JButton_NewCCAccount = new javax.swing.JButton();

	javax.swing.JButton JButton_Deposit = new javax.swing.JButton();
	javax.swing.JButton JButton_Charge = new javax.swing.JButton();
	javax.swing.JButton JButton_AddInterest = new javax.swing.JButton();
	javax.swing.JButton JButton_Transactions = new javax.swing.JButton();
	javax.swing.JButton JButton_MonthlyReport = new javax.swing.JButton();
	javax.swing.JButton JButton_Exit = new javax.swing.JButton();
	JButton JButton_ErrorMessage = new JButton();

	AccountService accountService = new AccountService();

	public CardFrm() {
		// thisframe = this;

		setTitle("Credit-card processing Application.");
		setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(750, 450);
		setVisible(false);
		JPanel1.setLayout(null);
		getContentPane().add(BorderLayout.CENTER, JPanel1);
		JPanel1.setBounds(0, 0, 700, 400);
		/*
		 * /Add five buttons on the pane /for Adding personal account, Adding
		 * company account /Deposit, Withdraw and Exit from the system
		 */
		JScrollPane1 = new JScrollPane();
		model = new DefaultTableModel();
		JTable1 = new JTable(model);
		model.addColumn("AccountNr");
		model.addColumn("Name");
		model.addColumn("CC number");
		model.addColumn("Exp date");
		model.addColumn("Type");
		model.addColumn("Balance");
		rowdata = new Object[7];
		hitSubmit = false;

		JPanel1.add(JScrollPane1);
		JScrollPane1.setBounds(12, 92, 444, 160);
		JScrollPane1.getViewport().add(JTable1);
		JTable1.setBounds(0, 0, 420, 0);
		// rowdata = new Object[8];

		JButton_NewCCAccount.setText("Add CC account");
		JPanel1.add(JButton_NewCCAccount);
		JButton_NewCCAccount.setBounds(10, 20, 192, 33);

		JButton_Transactions.setText("Transactions");
		JButton_Transactions.setActionCommand("jbutton");
		JPanel1.add(JButton_Transactions);
		JButton_Transactions.setBounds(210, 20, 192, 33);
		JButton_Transactions.setActionCommand("jbutton");

		JButton_MonthlyReport.setText("Report");
		JPanel1.add(JButton_MonthlyReport);
		JButton_MonthlyReport.setBounds(420, 20, 192, 33);
		JButton_MonthlyReport.setActionCommand("jbutton");

		JButton_AddInterest.setText("Add Interest");
		JPanel1.add(JButton_AddInterest);
		JButton_AddInterest.setBounds(468, 90, 192, 33);

		JButton_Deposit.setText("Deposit");
		JPanel1.add(JButton_Deposit);
		JButton_Deposit.setBounds(468, 130, 192, 33);

		JButton_Charge.setText("Charge");
		JPanel1.add(JButton_Charge);
		JButton_Charge.setBounds(468, 170, 192, 33);

		JButton_Exit.setText("Exit");
		JPanel1.add(JButton_Exit);
		JButton_Exit.setBounds(468, 220, 192, 33);

		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);

		/*
		 * JButton_NewCCAccount.addActionListener((event) ->
		 * addCreditCardAccountAction(event));
		 * JButton_Deposit.addActionListener((event) -> depoistAction(event));
		 * JButton_Charge.addActionListener((event) -> chargeAction(event));
		 * JButton_Exit.addActionListener((event) -> exitAction(event));
		 * JButton_AddInterest.addActionListener((event) -> addInterest(event));
		 * JButton_BillingReport.addActionListener((event) ->
		 * showTransactionAction(event));
		 */

	}

	/*****************************************************
	 * The entry point for this application. Sets the Look and Feel to the
	 * System Look and Feel. Creates a new JFrame1 and makes it visible.
	 *****************************************************/
	static public void main(String args[]) {
		try {
			// Add the following code if you want the Look and Feel
			// to be set to the Look and Feel of the native system.

			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			}

			// Create a new instance of our application's frame, and make it
			// visible.
			CardFrm cardFrm = new CardFrm();
			cardFrm.initialize();
			cardFrm.setVisible(true);
			// (new CardFrm()).setVisible(true);
		} catch (Throwable t) {
			t.printStackTrace();
			// Ensure the application exits with an error condition.
			System.exit(1);
		}
	}

	@Override
	public void initialize() {
		IGUIFactory iguiFactory = getGUIFactory();
		iguiFactory.getHeaderPane().initialize();
		iguiFactory.getContentPane().initialize();
		iguiFactory.getRightPane().initialize();

	}

	@Override
	public IGUIFactory getGUIFactory() {
		return new CardGUIFactory(this);
	}

	void exitApplication() {
		try {
			this.setVisible(false); // hide the Frame
			this.dispose(); // free the system resources
			System.exit(0); // close the application
		} catch (Exception e) {
		}
	}

	class SymWindow extends java.awt.event.WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent event) {
			Object object = event.getSource();
			if (object == CardFrm.this)
				BankFrm_windowClosing(event);
		}
	}

	void BankFrm_windowClosing(java.awt.event.WindowEvent event) {
		// to do: code goes here.

		BankFrm_windowClosing_Interaction1(event);
	}

	void BankFrm_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
		try {
			this.exitApplication();
		} catch (Exception e) {
		}
	}

	

	void redrawTable() {
		// re-populate the table
		List<IAccount> accounts = accountService.getAllAccounts();
		model.setRowCount(0);
		for (int i = accounts.size() - 1; i >= 0; i--) {
			IAccount account = accounts.get(i);
			addToTable(account, account.getCustomer());
		}
	}

	void addToTable(IAccount account, ICustomer customer) {
		rowdata[0] = account.getAccountNo().toString();
		rowdata[1] = customer.getName();
		rowdata[2] = ((CCardAccount) account).getCCNumber();
		rowdata[3] = ((CCardAccount) account).getExpDate();

		if (account instanceof GoldAccount) {
			rowdata[4] = "Gold";
		} else if (account instanceof SilverAccount) {
			rowdata[4] = "Silver";
		} else if (account instanceof BronzeAccount) {
			rowdata[4] = "Bronze";
		}
		rowdata[5] = String.valueOf(account.getCurrentBalance());

		model.addRow(rowdata);
		JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
	}

}
