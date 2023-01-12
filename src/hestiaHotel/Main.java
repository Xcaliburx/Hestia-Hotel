package hestiaHotel;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Controller.EmployeeController;
import Model.Employee;
import Model.Session;
import View.BellStaffView;
import View.JournalView;
import View.MaintenanceStaffView;
import View.ManagerView;
import View.ReceptionistView;
import View.SecurityStaffView;

public class Main extends JFrame implements ActionListener{

	JLabel lblTitle,lblUsername,lblPassword;
	JTextField txtUsername;
	JPasswordField txtPassword;
	JButton btnSubmit;
	JPanel topPanel,midPanel,botPanel,midFlowLayout;
	
	public Main() {
		// TODO Auto-generated constructor stub
		initFrame();
		setComponent();
		
		setVisible(true);
	}

	public void initFrame(){
		setSize(420,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Hestia Hotel");
		setResizable(false);
		setLayout(new BorderLayout());
	}
	
	private void setComponent() {
		// TODO Auto-generated method stub
		lblTitle = new JLabel("Login");
		lblUsername = new JLabel("Username : ");
		lblPassword = new JLabel("Password : ");
		txtUsername = new JTextField();
		txtPassword = new JPasswordField();
		btnSubmit = new JButton("Login");
		topPanel = new JPanel();
		midPanel = new JPanel();
		midFlowLayout = new JPanel();
		botPanel = new JPanel();
		
		topPanel.add(lblTitle);
		midPanel.setLayout(new GridLayout(2,2));
		midPanel.add(lblUsername);
		midPanel.add(txtUsername);
		midPanel.add(lblPassword);
		midPanel.add(txtPassword);
		
		btnSubmit.addActionListener(this);
		botPanel.add(btnSubmit);
		
		add(topPanel,BorderLayout.NORTH);
		add(midPanel,BorderLayout.CENTER);
		add(botPanel,BorderLayout.SOUTH);
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource() == btnSubmit){
			if(txtUsername.getText().equals("") || txtPassword.getText().equals("")){
				JOptionPane.showMessageDialog(this, "Username & Password cannot be empty !");
			}else{
				Employee e = EmployeeController.getEmployee(txtUsername.getText());
				if(e == null){
					Session.employee = null;
					JOptionPane.showMessageDialog(this, "Username not found");
				}else{
					Session.setEmployee(e);
					if(Session.employee.getEmployeePassword().equals(txtPassword.getText())){
						if(!Session.employee.getIsFired()){
							if(Session.employee.getEmployeeRole().equals("manager")){
								new ManagerView();
								this.dispose();
							} else if(Session.employee.getEmployeeRole().equals("receptionist")){
								new ReceptionistView();
								this.dispose();
							} else if(Session.employee.getEmployeeRole().equals("security staff")){
								new SecurityStaffView();
								this.dispose();
							}else if(Session.employee.getEmployeeRole().equals("bell staff")){
								new BellStaffView();
								this.dispose();
							} else {
								new MaintenanceStaffView();
								this.dispose();
							}
						}else{
							JOptionPane.showMessageDialog(this, "You cannot login");
						}
					}else{
						JOptionPane.showMessageDialog(this, "Username and Password not matched");
						Session.employee = null;
					}
				}
			}
		}
	}
}
