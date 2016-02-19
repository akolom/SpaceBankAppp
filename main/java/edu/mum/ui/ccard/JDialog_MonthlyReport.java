package edu.mum.ui.ccard;
/*
		A basic implementation of the JDialog class.
*/

import java.awt.Frame;
import java.util.List;

import edu.mum.ccard.models.ChargeEntry;
import edu.mum.framework.IEntry;

public class JDialog_MonthlyReport extends javax.swing.JDialog {
	String billstring;

	public JDialog_MonthlyReport(Frame parent, String aaccnr, List<IEntry> entries) {
		super(parent);

		getContentPane().setLayout(null);
		setSize(350, 450);
		setVisible(false);
		getContentPane().add(JScrollPane1);
		JScrollPane1.setBounds(24, 24, 340, 440);
		JScrollPane1.getViewport().add(JTextField1);
		
		JTextField1.setBounds(0, 0, 355, 237);
		JButton_OK.setText("OK");
		JButton_OK.setActionCommand("OK");
		getContentPane().add(JButton_OK);
		JButton_OK.setBounds(156, 276, 96, 24);

		// generate the string for the monthly bill
		
		
		String reportText = generateReport(entries);
		JTextField1.setText(reportText);
		// }}

		// {{REGISTER_LISTENERS
		SymAction lSymAction = new SymAction();
		JButton_OK.addActionListener(lSymAction);
		// }}
	}

	private String generateReport(List<IEntry> entries){
		String reportData = "";
		//previous balance: balance from last month
		//total charges: total of all charges for this month
		//total credits: total of all payments for this month
		//new balance = previous balance – total credits + total charges + MI * (previous balance
		//total credits)
		// total due = MP * new balance
		double charges = 0.0;
		double deposits =0.0;
		
		for(IEntry entry: entries){
			if(entry instanceof ChargeEntry){
				charges +=entry.getAmount();
			}else {
				deposits +=entry.getAmount();
			}
		}
		
		reportData += "Total Charges = $ " + charges + "\r\n";
		reportData += "Total Deposits = $ " + deposits + "\r\n";
		
		billstring = "Name= John White\r\n";
		billstring += "Address= 1000 Main, Fairfield, IA, 52556\r\n";
		billstring += "CC number= 2341 3421 4444 5689\r\n";
		billstring += "CC type= GOLD\r\n";
		billstring += "Previous balance = $ 100.00\r\n";
		
		billstring += "New balance = $ 638.75\r\n";
		billstring += "Total amount due = $ 63.88\r\n";
		billstring += "\r\n";
		billstring += "\r\n";
		billstring += "Name= Frank Summer\r\n";
		billstring += "Address= 1000 N, 4th St, Fairfield, IA, 52556\r\n";
		billstring += "CC number= 0099 3421 4321 6577\r\n";
		billstring += "CC type= BRONZE\r\n";
		billstring += "Previous balance = $ 200.00\r\n";
		billstring += "Total Credits = $ 45.00\r\n";
		billstring += "Total Charges = $ 150.00\r\n";
		billstring += "New balance = $ 313.53\r\n";
		billstring += "Total amount due = $ 34.49\r\n";
		
		return reportData;
	}

	// {{DECLARE_CONTROLS
	javax.swing.JScrollPane JScrollPane1 = new javax.swing.JScrollPane();
	javax.swing.JTextArea JTextField1 = new javax.swing.JTextArea();
	javax.swing.JButton JButton_OK = new javax.swing.JButton();
	// }}

	class SymAction implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent event) {
			Object object = event.getSource();
			if (object == JButton_OK)
				JButtonOK_actionPerformed(event);
		}
	}

	void JButtonOK_actionPerformed(java.awt.event.ActionEvent event) {
		dispose();

	}
}
