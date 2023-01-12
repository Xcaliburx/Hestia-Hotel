package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import Controller.CustomerRequestController;
import Model.CustomerRequest;
import Model.Session;

public class CustomerRequestView extends JFrame implements ActionListener{

	public CustomerRequestView() {
		// TODO Auto-generated constructor stub
		initFrame();
		setComponent();
		getAllData();
		
		setVisible(true);
	}
	JLabel lblNote, lblID;
	JButton btnFinish, btnInsert;
	JTextField txtNote, txtID;
	JPanel botPanel;
	
	JTable table;
	DefaultTableModel dtm;
	Vector<Object> tableHeader, tableData;
	Vector<Integer> vectID;
	
	private void setComponent() {
		// TODO Auto-generated method stub
		lblID = new JLabel("ID : ");
		vectID = new Vector<>();
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
	    txtID = new JFormattedTextField(formatter);
	    
	    lblNote = new JLabel("Note : ");
	    txtNote = new JTextField();
	    
		btnFinish = new JButton("Finish");
		btnInsert = new JButton("Insert");
		btnFinish.addActionListener(this);
		btnInsert.addActionListener(this);
		
		tableHeader = new Vector<>();
		tableHeader.add(new String("Customer Request ID"));
		tableHeader.add(new String("Receptionist ID"));
		tableHeader.add(new String("Room ID"));
		tableHeader.add(new String("Request"));
		tableHeader.add(new String("Request Date Time"));
		
		dtm = new DefaultTableModel(tableHeader, 0);
		table = new JTable(dtm);
		add(table,BorderLayout.NORTH);
		botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(3,2));
		if(Session.employee.getEmployeeRole().equals("bell staff") || Session.employee.getEmployeeRole().equals("maintenance staff")) {
			botPanel.add(lblID);
			botPanel.add(txtID);
			botPanel.add(lblNote);
			botPanel.add(txtNote);
			botPanel.add(btnFinish);
		} else if (Session.employee.getEmployeeRole().equals("receptionist")) {
			botPanel.add(btnInsert);
		}
		
		add(botPanel,BorderLayout.SOUTH);
	}

	private void getAllData() {
		// TODO Auto-generated method stub
		Vector<CustomerRequest> em = CustomerRequestController.getInstance().getAll();
		dtm.setRowCount(0);
		vectID.clear();
		dtm.addRow(tableHeader);
		
		try {
			for( CustomerRequest x : em) {
				tableData = new Vector<>();
				tableData.add(x.getCustomerRequestId());
				tableData.add(x.getReceptionistId());
				tableData.add(x.getRoomId());
				tableData.add(x.getRequest());
				tableData.add(x.getRequestDateTime());
				vectID.add(x.getCustomerRequestId());
				dtm.addRow(tableData);
			}
		
			table.setModel(dtm);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	public void initFrame(){
		setSize(700,560);
		setLocationRelativeTo(null);
		setTitle("Hestia Hotel");
		setResizable(false);
		setLayout(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = txtID.getText().replace(",", "");
		
		JOptionPane jop = new JOptionPane();
		if(e.getSource() == btnInsert) {
			CustomerRequestController.viewInsertRequestForm();
			this.dispose();
		} else if(id.equals("")){
			JOptionPane.showMessageDialog(this, "ID cannot be empty");
		} else if(txtNote.getText().equals("")){
			JOptionPane.showMessageDialog(this, "Notes cannot be empty");
		} else{
			if(e.getSource() == btnFinish){
				boolean exist = false;
				for (Integer i : vectID) {
					if(i == Integer.parseInt(id)){
						exist = true;
						break;
					}
				}
				if(exist){
					int choose = JOptionPane.showConfirmDialog(this, "Are you sure want to finish request with id " + id,id, jop.YES_NO_OPTION);
					if(choose == 0){
						if(CustomerRequestController.updateRequest(txtNote.getText(), Integer.parseInt(txtID.getText()))) {
							JOptionPane.showMessageDialog(this, "Finish Request Success");
							getAllData();
						}
					}
				}else{
					JOptionPane.showMessageDialog(this, "ID is invalid");
				}
			}
		}
	}
	
}
