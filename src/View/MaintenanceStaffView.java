package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.AttendanceController;
import Controller.CustomerRequestController;

public class MaintenanceStaffView extends JFrame implements ActionListener{
	JButton btnAttendance, btnRequest;
	
	private void setComponent() {
		// TODO Auto-generated method stub
		btnAttendance = new JButton("View Attendance");
		btnRequest = new JButton("View Request");
	
		btnAttendance.addActionListener(this);
		btnRequest.addActionListener(this);
		
		JPanel botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(4,1));
		botPanel.add(btnAttendance);
		botPanel.add(btnRequest);
		add(botPanel,BorderLayout.CENTER);
	}

	public void initFrame(){
		setSize(420,210);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Hotel Hestia");
		setResizable(false);
		setLayout(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnAttendance){
			AttendanceController.viewAllAttendance();
		} else if(e.getSource() == btnRequest){
			CustomerRequestController.viewAllRequest();
		}
	}
	public MaintenanceStaffView() {
		// TODO Auto-generated constructor stub
		initFrame();
		setComponent();
		
		setVisible(true);
	}
}
