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

public class InsertEmployeeView extends JFrame implements ActionListener{

	public InsertEmployeeView() {
		// TODO Auto-generated constructor stub
		initFrame();
		setComponent();
		
		setVisible(true);
	}
	
	JComboBox<String> roleComboBox;
	JLabel lblName, lblPassword, lblGender, lblBirthDate, lblUsername, lblEmail;
	JTextField txtName, txtPassword, txtGender, txtBirthDate, txtUsername, txtEmail;
	JPanel topPanel,midPanel,botPanel;
	JButton btnSubmit;

	private void setComponent() {
		// TODO Auto-generated method stub
		String[] data = {"manager", "receptionist", "security staff", "bell staff", "maintenance staff"};
		roleComboBox = new JComboBox<>(data);
		roleComboBox.setSelectedIndex(1);
		roleComboBox.addActionListener(this);
		
		lblUsername = new JLabel("Username : ");
		lblPassword = new JLabel("Password : ");
		lblEmail = new JLabel("Email : ");
		lblName = new JLabel("Name : ");
		lblGender = new JLabel("Gender : ");
		lblBirthDate = new JLabel("Birth Date : ");
		txtUsername = new JTextField();
		txtPassword = new JTextField();
		txtEmail = new JTextField();
		txtName = new JTextField();
		txtGender = new JTextField();
		txtBirthDate = new JTextField();

	    btnSubmit = new JButton("Insert");
		topPanel = new JPanel();
		midPanel = new JPanel();
		botPanel = new JPanel();
	    
		midPanel.setLayout(new GridLayout(9,2));
		midPanel.add(lblUsername);
		midPanel.add(txtUsername);
		midPanel.add(lblPassword);
		midPanel.add(txtPassword);
		midPanel.add(lblEmail);
		midPanel.add(txtEmail);
		midPanel.add(lblName);
		midPanel.add(txtName);
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
		setTitle("Insert Employee");
		setResizable(false);
		setLayout(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnSubmit){			
			if(txtUsername.getText().equals("") || txtEmail.getText().equals("") ||
				txtName.getText().equals("") || txtPassword.getText().equals("") || 
					txtGender.getText().equals("") || txtBirthDate.getText().equals("")){
				JOptionPane.showMessageDialog(this, "Data cannot be empty");
			} else if(txtPassword.getText().length() < 6) {
				JOptionPane.showMessageDialog(this, "Password must be at least 6 character");
			} else if(txtEmail.getText().length() < 3 || !txtEmail.getText().contains("@")) {
				JOptionPane.showMessageDialog(this, "Email must at least contains an @ and 3 characters long");
			} else if(!txtGender.getText().equals("male") && !txtGender.getText().equals("female")) {
				JOptionPane.showMessageDialog(this, "Gender must be either “male” or “female”");
			} else {
				if(EmployeeController.addEmployee(txtUsername.getText(), txtPassword.getText(), txtEmail.getText(), txtName.getText(), txtGender.getText(), txtBirthDate.getText(), roleComboBox.getSelectedItem().toString())){
					JOptionPane.showMessageDialog(this, "Insert Data Success");
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Insert Data Failed");
				}
			}
		}
	}
}
