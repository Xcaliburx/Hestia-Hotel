package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.AttendanceController;
import Controller.CheckInController;
import Controller.CustomerRequestController;
import Controller.EmployeeController;
import Controller.JournalController;
import Controller.RoomController;

public class ManagerView extends JFrame implements ActionListener{
	JButton btnEmployee, btnRoom, btnAttendance, btnJournal, btnRequest, btnCheckIn;
	
	private void setComponent() {
		// TODO Auto-generated method stub
		btnEmployee = new JButton("View Employee");
		btnRoom = new JButton("View Room");
		btnAttendance = new JButton("View Attendance");
		btnJournal = new JButton("View Journal");
		btnRequest = new JButton("View Request");
		btnCheckIn = new JButton("View Check In List");
	
		btnEmployee.addActionListener(this);
		btnRoom.addActionListener(this);
		btnAttendance.addActionListener(this);
		btnJournal.addActionListener(this);
		btnRequest.addActionListener(this);
		btnCheckIn.addActionListener(this);
		
		JPanel botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(4,1));
		botPanel.add(btnEmployee);
		botPanel.add(btnRoom);
		botPanel.add(btnAttendance);
		botPanel.add(btnJournal);
		botPanel.add(btnRequest);
		botPanel.add(btnCheckIn);
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
		if(e.getSource() == btnEmployee){
			EmployeeController.viewAllEmployee();
		} else if(e.getSource() == btnRoom){
			RoomController.viewAllRoom();
		} else if(e.getSource() == btnAttendance){
			AttendanceController.viewAllAttendance();
		}  else if(e.getSource() == btnJournal){
			JournalController.viewAllJOurnal();
		}  else if(e.getSource() == btnRequest){
			CustomerRequestController.viewAllRequest();
		} else if(e.getSource() == btnCheckIn){
			CheckInController.viewAllCheckIn();
		}
	}
	public ManagerView() {
		// TODO Auto-generated constructor stub
		initFrame();
		setComponent();
		
		setVisible(true);
	}
}
