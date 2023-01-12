package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.EmployeeController;
import Model.Employee;

public class UpdateEmployeeView extends JFrame implements ActionListener{

	int eID;
	public UpdateEmployeeView(String id) {
		// TODO Auto-generated constructor stub
		eID = Integer.parseInt(id);
		initFrame();
		setComponent();
		setVisible(true);
	}

	JComboBox<String> roleComboBox;
	JLabel lblName, lblPassword, lblGender, lblBirthDate;
	JTextField txtName, txtPassword, txtGender, txtBirthDate;
	JPanel topPanel,midPanel,botPanel;
	JButton btnSubmit;
	
	private void setComponent() {
		// TODO Auto-generated method stub
		String[] data = {"manager", "receptionist", "security staff", "bell staff", "maintenance staff"};
		roleComboBox = new JComboBox<>(data);
		roleComboBox.setSelectedIndex(1);
		roleComboBox.addActionListener(this);
			
		Employee e = EmployeeController.getEmployeebyId(eID);
		
		lblName = new JLabel("Name : ");
		lblPassword = new JLabel("Password : ");
		lblGender = new JLabel("Gender : ");
		lblBirthDate = new JLabel("Birth Date : ");
		txtName = new JTextField();
		txtName.setText(e.getEmployeeName());
		txtPassword = new JTextField();
		txtPassword.setText(e.getEmployeePassword());
		txtGender = new JTextField();
		txtGender.setText(e.getEmployeeGender());
		txtBirthDate = new JTextField();
		txtBirthDate.setText(e.getEmployeeBirthDate());
		roleComboBox.setSelectedItem(e.getEmployeeRole());

	    btnSubmit = new JButton("Update");
		topPanel = new JPanel();
		midPanel = new JPanel();
		botPanel = new JPanel();
	    
		midPanel.setLayout(new GridLayout(6,2));
		midPanel.add(lblName);
		midPanel.add(txtName);
		midPanel.add(lblPassword);
		midPanel.add(txtPassword);
		midPanel.add(lblGender);
		midPanel.add(txtGender);
		midPanel.add(lblBirthDate);
		midPanel.add(txtBirthDate);
		midPanel.add(new JLabel("Role : "));
		midPanel.add(roleComboBox);
		
		btnSubmit.addActionListener(this);
		botPanel.add(btnSubmit);
		
		add(topPanel,BorderLayout.NORTH);
		add(midPanel,BorderLayout.CENTER);
		add(botPanel,BorderLayout.SOUTH);
	}

	public void initFrame(){
		setSize(560,260);
		setLocationRelativeTo(null);
		setTitle("Update Employee");
		setResizable(false);
		setLayout(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnSubmit){			
			if(txtName.getText().equals("") || txtPassword.getText().equals("") || 
					txtGender.getText().equals("") || txtBirthDate.getText().equals("")){
				JOptionPane.showMessageDialog(this, "Data cannot be empty");
			} else if(txtPassword.getText().length() < 6) {
				JOptionPane.showMessageDialog(this, "Password must be at least 6 character");
			} else if(!txtGender.getText().equals("male") && !txtGender.getText().equals("female")) {
				JOptionPane.showMessageDialog(this, "Gender must be either “male” or “female”");
			} else {
				if(EmployeeController.updateEmployee(txtPassword.getText(), txtName.getText(), txtGender.getText(), txtBirthDate.getText(), roleComboBox.getSelectedItem().toString(), eID)){
					JOptionPane.showMessageDialog(this, "Update Data Success");
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Update Data Failed");
				}
			}
		}
	}
}
