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

import Controller.EmployeeController;
import Model.Employee;
import Model.Session;

public class EmployeeView extends JFrame implements ActionListener{

	public EmployeeView() {
		// TODO Auto-generated constructor stub
		initFrame();
		setComponent();
		getAllData();
		
		setVisible(true);
	}
	JLabel lblID;
	JButton btnUpdate, btnDelete, btnInsert;
	JTextField txtID;
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
	    
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Fire");
		btnInsert = new JButton("Insert");
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnInsert.addActionListener(this);
		
		tableHeader = new Vector<>();
		tableHeader.add(new String("Employee ID"));
		tableHeader.add(new String("Employee Name"));
		tableHeader.add(new String("Employee Gender"));
		tableHeader.add(new String("Employee BirthDate"));
		tableHeader.add(new String("Employee Role"));	
		tableHeader.add(new String("Is Fired"));	
		
		dtm = new DefaultTableModel(tableHeader, 0);
		table = new JTable(dtm);
		add(table,BorderLayout.NORTH);
		botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(3,2));
		botPanel.add(lblID);
		botPanel.add(txtID);
		botPanel.add(btnUpdate);
		botPanel.add(btnDelete);
		botPanel.add(btnInsert);
		add(botPanel,BorderLayout.SOUTH);
	}

	private void getAllData() {
		// TODO Auto-generated method stub
		Vector<Employee> em = EmployeeController.getInstance().getAll();
		dtm.setRowCount(0);
		vectID.clear();
		dtm.addRow(tableHeader);
		
		try {
			for( Employee x : em) {
				tableData = new Vector<>();
				tableData.add(x.getEmployeeId());
				tableData.add(x.getEmployeeName());
				tableData.add(x.getEmployeeGender());
				tableData.add(x.getEmployeeBirthDate());
				tableData.add(x.getEmployeeRole());
				tableData.add(x.getIsFired());
				vectID.add(x.getEmployeeId());
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
		String id = txtID.getText().replace(",", "");
		
		JOptionPane jop = new JOptionPane();
		if(e.getSource() == btnInsert) {
			EmployeeController.viewInsertEmployeeForm();
			this.dispose();
		} else if(id.equals("")){
			JOptionPane.showMessageDialog(this, "ID cannot be empty");
		} else{
			if(e.getSource() == btnDelete){
				boolean exist = false;
				for (Integer i : vectID) {
					if(i == Integer.parseInt(id)){
						exist = true;
						break;
					}
				}
				if(exist){
					int choose = JOptionPane.showConfirmDialog(this, "Are you sure want to fire employee with id " + id,id, jop.YES_NO_OPTION);
					if(choose == 0){
						if (Session.employee != null && Integer.parseInt(id) == Session.employee.getEmployeeId()) {
							JOptionPane.showMessageDialog(this, "You cannot fire yourself");
						}
						else if(EmployeeController.fireEmployee(Integer.parseInt(id)) ){
							JOptionPane.showMessageDialog(this, "Fire Success");
							getAllData();
						}
					}
				}else{
					JOptionPane.showMessageDialog(this, "ID is invalid");
				}
			} else if(e.getSource() == btnUpdate){
				boolean exist = false;
				for (Integer i : vectID) {
					if(i == Integer.parseInt(id)){
						exist = true;
						break;
					}
				}
				if(exist){
					EmployeeController.viewUpdateEmployee(id);
					this.dispose();
				}else{
					JOptionPane.showMessageDialog(this, "ID is invalid");
				}
			}
		}
		
	}
	
}
