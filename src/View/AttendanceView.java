package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.AttendanceController;
import Model.Attendance;

public class AttendanceView extends JFrame implements ActionListener{

	public AttendanceView() {
		// TODO Auto-generated constructor stub
		initFrame();
		setComponent();
		getAllData();
		
		setVisible(true);
	}
	
	JComboBox<String> typeComboBox;
	JButton btnInsert;
	JPanel botPanel;
	
	JTable table;
	DefaultTableModel dtm;
	Vector<Object> tableHeader, tableData;
	
	private void setComponent() {
		// TODO Auto-generated method stub
		String[] data = {"clock out", "clock in"};
		typeComboBox = new JComboBox<>(data);
		typeComboBox.setSelectedIndex(1);
		typeComboBox.addActionListener(this);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		
		tableHeader = new Vector<>();
		tableHeader.add(new String("Employee ID"));
		tableHeader.add(new String("Attendance Date Time"));
		tableHeader.add(new String("Attendance Type"));
		
		dtm = new DefaultTableModel(tableHeader, 0);
		table = new JTable(dtm);
		add(table,BorderLayout.NORTH);
		botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(2,2));
		botPanel.add(new JLabel("Attendance Type : "));
		botPanel.add(typeComboBox);
		botPanel.add(btnInsert);
		add(botPanel,BorderLayout.SOUTH);
	}

	private void getAllData() {
		// TODO Auto-generated method stub
		Vector<Attendance> em = AttendanceController.getInstance().getAll();
		dtm.setRowCount(0);
		dtm.addRow(tableHeader);
		
		try {
			for( Attendance x : em) {
				tableData = new Vector<>();
				tableData.add(x.getEmployeeId());
				tableData.add(x.getAttendanceDateTime());
				tableData.add(x.getAttendanceType());
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
			if(AttendanceController.addAttendance(typeComboBox.getSelectedItem().toString())){
				JOptionPane.showMessageDialog(this, "Insert Data Success");
				getAllData();
			} else {
				JOptionPane.showMessageDialog(this, "Insert Data Failed");
			}
		}
	}
	
}
