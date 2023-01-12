package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.JournalController;
import Controller.RoomController;
import Model.Journal;
import Model.Session;

public class JournalView extends JFrame implements ActionListener{

	public JournalView() {
		// TODO Auto-generated constructor stub
		initFrame();
		setComponent();
		getAllData();
		
		setVisible(true);
	}
	JLabel lblMessage;
	JButton btnInsert;
	JTextField txtMessage;
	JPanel botPanel;
	
	JTable table;
	DefaultTableModel dtm;
	Vector<Object> tableHeader, tableData;
	
	private void setComponent() {
		// TODO Auto-generated method stub
		lblMessage = new JLabel("Message : ");
	    txtMessage = new JTextField();
	    
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		
		tableHeader = new Vector<>();
		tableHeader.add(new String("Journal Id"));
		tableHeader.add(new String("Security Staff Id"));
		tableHeader.add(new String("Journal Date"));
		tableHeader.add(new String("Journal Message"));
		
		dtm = new DefaultTableModel(tableHeader, 0);
		table = new JTable(dtm);
		add(table,BorderLayout.NORTH);
		botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(3,2));
		if (Session.employee.getEmployeeRole().equals("security staff")) {
			botPanel.add(lblMessage);
			botPanel.add(txtMessage);
			botPanel.add(btnInsert);
		}
		add(botPanel,BorderLayout.SOUTH);
	}

	private void getAllData() {
		// TODO Auto-generated method stub
		Vector<Journal> em = JournalController.getInstance().getAll();
		dtm.setRowCount(0);
		dtm.addRow(tableHeader);
		
		try {
			for( Journal x : em) {
				tableData = new Vector<>();
				tableData.add(x.getJournalId());
				tableData.add(x.getSecurityStaffId());
				tableData.add(x.getJournalDate());
				tableData.add(x.getJournalMessage());
				dtm.addRow(tableData);
			}
		
			table.setModel(dtm);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	public void initFrame(){
		setSize(560,560);
		setLocationRelativeTo(null);
		setTitle("Hestia Hotel");
		setResizable(false);
		setLayout(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		JOptionPane jop = new JOptionPane();
		if(e.getSource() == btnInsert) {
			if(txtMessage.getText().equals("")){
				JOptionPane.showMessageDialog(this, "Data cannot be empty");
			} else {
				if(JournalController.addJournal(txtMessage.getText())){
					JOptionPane.showMessageDialog(this, "Insert Data Success");
				} else {
					JOptionPane.showMessageDialog(this, "Insert Data Failed");
				}
			}
			getAllData();
		} 
	}
	
}
