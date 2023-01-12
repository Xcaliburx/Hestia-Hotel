package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.CheckInController;
import Model.CheckIn;
import Model.Session;

public class CheckInView extends JFrame implements ActionListener{

	public CheckInView() {
		// TODO Auto-generated constructor stub
		initFrame();
		setComponent();
		getAllData();
		
		setVisible(true);
	}
	
	JButton btnInsert;
	JPanel botPanel;
	
	JTable table;
	DefaultTableModel dtm;
	Vector<Object> tableHeader, tableData;
	
	private void setComponent() {
		// TODO Auto-generated method stub
	   
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		
		tableHeader = new Vector<>();
		tableHeader.add(new String("Check In Id"));
		tableHeader.add(new String("Date Time"));
		tableHeader.add(new String("Receptionist Id"));
		tableHeader.add(new String("Number of customer"));
		tableHeader.add(new String("Room Id"));
		
		dtm = new DefaultTableModel(tableHeader, 0);
		table = new JTable(dtm);
		add(table,BorderLayout.NORTH);
		botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(1,1));
		if(Session.employee.getEmployeeRole().equals("receptionist")) {
			botPanel.add(btnInsert);
		}
		add(botPanel,BorderLayout.SOUTH);
	}

	private void getAllData() {
		// TODO Auto-generated method stub
		Vector<CheckIn> em = CheckInController.getInstance().getAll();
		dtm.setRowCount(0);
		dtm.addRow(tableHeader);
		
		try {
			for( CheckIn x : em) {
				tableData = new Vector<>();
				tableData.add(x.getCheckInId());
				tableData.add(x.getDateTime());
				tableData.add(x.getReceptionistId());
				tableData.add(x.getNumberOfCustomer());
				tableData.add(x.getRoomId());
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
			CheckInController.viewInsertCheckInForm();
			this.dispose();
		} 
	}
}
