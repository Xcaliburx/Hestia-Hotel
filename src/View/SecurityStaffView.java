package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.AttendanceController;
import Controller.JournalController;

public class SecurityStaffView extends JFrame implements ActionListener{
	JButton btnAttendance, btnJournaling;
	
	private void setComponent() {
		// TODO Auto-generated method stub
		btnAttendance = new JButton("View Attendance");
		btnJournaling = new JButton("View Journal");
	
		btnAttendance.addActionListener(this);
		btnJournaling.addActionListener(this);
		
		JPanel botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(4,1));
		botPanel.add(btnAttendance);
		botPanel.add(btnJournaling);
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
		} else if(e.getSource() == btnJournaling){
			JournalController.viewAllJOurnal();
		}
	}
	public SecurityStaffView() {
		// TODO Auto-generated constructor stub
		initFrame();
		setComponent();
		
		setVisible(true);
	}
}
