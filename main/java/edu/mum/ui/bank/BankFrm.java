package edu.mum.ui.bank;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

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
import edu.mum.framework.command.CreateAccountCommand;
import edu.mum.framework.command.ICommand;
import edu.mum.framework.command.TransactionCommand;
import edu.mum.framework.service.AccountService;
import edu.mum.framework.service.TransactionService;
import edu.mum.framework.ui.FrameWorkInitializer;
import edu.mum.framework.ui.IGUIFactory;

/**
 * A basic JFC based application.
 */
public class BankFrm extends JFrame implements FrameWorkInitializer {

	private static final long serialVersionUID = 1L;
	/****
	 * init variables in the object
	 ****/
	public String accountnr, clientName, street, city, zip, state, dob, email, no_of_employees, accountType, clientType,
			amountDeposit;
	public boolean hitSubmit;
	public DefaultTableModel model;
	public JTable JTable1;
	private JScrollPane JScrollPane1;
	//BankFrm myframe;
	public Object rowdata[];

	JPanel JPanel1 = new JPanel();
	JButton JButton_PerAC = new JButton();
	JButton JButton_CompAC = new JButton();
	JButton JButton_Deposit = new JButton();
	JButton JButton_Withdraw = new JButton();
	JButton JButton_Addinterest = new JButton();
	JButton JButton_ErrorMessage = new JButton();
	JButton JButton_ShowTransaction = new JButton();
	JButton JButton_Exit = new JButton();
	
	AccountService accountService = new AccountService();

	public BankFrm() {
		//myframe = this;

		setTitle("Bank Application.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(750, 450);
		setVisible(false);
		JPanel1.setLayout(null);
		getContentPane().add(BorderLayout.CENTER, JPanel1);
		JPanel1.setBounds(0, 0, 575, 310);
		/*
		 * /Add five buttons on the pane /for Adding personal account, Adding
		 * company account /Deposit, Withdraw and Exit from the system
		 */
		JScrollPane1 = new JScrollPane();
		model = new DefaultTableModel();
		JTable1 = new JTable(model);
		model.addColumn("AccountNr");
		model.addColumn("Name");
		model.addColumn("City");
		model.addColumn("P/C");
		model.addColumn("Ch/S");
		model.addColumn("Amount");
		rowdata = new Object[8];
		hitSubmit = false;

		JPanel1.add(JScrollPane1);
		JScrollPane1.setBounds(12, 92, 444, 160);
		JScrollPane1.getViewport().add(JTable1);
		JTable1.setBounds(0, 0, 420, 0);
		// rowdata = new Object[8];

		JButton_PerAC.setText("Add personal account");
		JPanel1.add(JButton_PerAC);
		JButton_PerAC.setBounds(24, 20, 192, 33);
		JButton_CompAC.setText("Add company account");
		JButton_CompAC.setActionCommand("jbutton");
		JPanel1.add(JButton_CompAC);
		JButton_CompAC.setBounds(240, 20, 192, 33);

		JButton_Addinterest.setBounds(448, 20, 106, 33);
		JButton_Addinterest.setText("Add interest");
		JPanel1.add(JButton_Addinterest);

		JButton_Deposit.setText("Deposit");
		JPanel1.add(JButton_Deposit);
		JButton_Deposit.setBounds(468, 104, 186, 33);

		JButton_Withdraw.setText("Withdraw");
		JPanel1.add(JButton_Withdraw);
		JButton_Withdraw.setBounds(468, 144, 186, 33);

		JButton_ShowTransaction.setBounds(468, 184, 186, 33);
		JButton_ShowTransaction.setText("Show Transaction");
		JPanel1.add(JButton_ShowTransaction);

		JButton_Exit.setText("Exit");
		JPanel1.add(JButton_Exit);
		JButton_Exit.setBounds(468, 248, 186, 31);
		// lineBorder1.setRoundedCorners(true);
		// lineBorder1.setLineColor(java.awt.Color.green);
		// $$ lineBorder1.move(24,312);

		JButton_PerAC.setActionCommand("jbutton");
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
			BankFrm bankFrm = new BankFrm();
			bankFrm.initialize();
		bankFrm.setVisible(true);

		} catch (Throwable t) {
			t.printStackTrace();
			// Ensure the application exits with an error condition.
			System.exit(1);
		}
	}

	@Override
	public void initialize() {
		IGUIFactory guiFactory = getGUIFactory();
		guiFactory.getHeaderPane().initialize();
		guiFactory.getContentPane().initialize();
		guiFactory.getRightPane().initialize();
	}

	@Override
	public IGUIFactory getGUIFactory() {
		return new BankGUIFactory(this);
	}

	// When the Exit button is pressed this code gets executed
	// this will exit from the system
	void exitApplicationAction(java.awt.event.ActionEvent event) {
		this.setVisible(false); // hide the Frame
		this.dispose(); // free the system resources
		System.exit(0); // close the application
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
		rowdata[2] = customer.getCity();
		if (account.getCustomer() instanceof Person) {
			rowdata[3] = "P";
		} else if (account.getCustomer() instanceof Organization) {
			rowdata[3] = "C";
		}
		if (account instanceof CheckingAccount) {
			rowdata[4] = "Ch";
		} else if (account instanceof SavingAccount) {
			rowdata[4] = "S";
		}
		rowdata[5] = String.valueOf(account.getCurrentBalance());
		model.addRow(rowdata);
		JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
	}
	

}
