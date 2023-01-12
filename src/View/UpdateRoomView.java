package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import Controller.EmployeeController;
import Controller.RoomController;
import Model.Employee;
import Model.Room;

public class UpdateRoomView extends JFrame implements ActionListener{

	int eID;
	public UpdateRoomView(String id) {
		// TODO Auto-generated constructor stub
		eID = Integer.parseInt(id);
		initFrame();
		setComponent();
		setVisible(true);
	}

	JComboBox<String> typeComboBox;
	JLabel lblRoomId, lblRoomIdValue;
	JPanel topPanel,midPanel,botPanel;
	JButton btnSubmit;
	
	private void setComponent() {
		// TODO Auto-generated method stub
		String[] data = {"standard", "premium"};
		typeComboBox = new JComboBox<>(data);
		typeComboBox.setSelectedIndex(1);
		typeComboBox.addActionListener(this);

		Room r = RoomController.getRoombyId(eID);
		lblRoomId = new JLabel("Room Id : ");
		lblRoomIdValue = new JLabel(String.valueOf(r.getRoomId()));
		typeComboBox.setSelectedItem(r.getRoomType());
		
	    btnSubmit = new JButton("Update");
		topPanel = new JPanel();
		midPanel = new JPanel();
		botPanel = new JPanel();
	    
		midPanel.setLayout(new GridLayout(9,2));
		midPanel.add(lblRoomId);
		midPanel.add(lblRoomIdValue);
		midPanel.add(new JLabel("Room Type : "));
		midPanel.add(typeComboBox);
		
		btnSubmit.addActionListener(this);
		botPanel.add(btnSubmit);
		
		add(topPanel,BorderLayout.NORTH);
		add(midPanel,BorderLayout.CENTER);
		add(botPanel,BorderLayout.SOUTH);
	}

	public void initFrame(){
		setSize(560,260);
		setLocationRelativeTo(null);
		setTitle("Update Room");
		setResizable(false);
		setLayout(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnSubmit){			
			int price = 0;
			if (typeComboBox.getSelectedItem().toString().equals("standard")) {
				price = 8000;
			} else {
				price = 10000;
			}
			if(RoomController.updateRoom(typeComboBox.getSelectedItem().toString(), price, eID)){
				JOptionPane.showMessageDialog(this, "Update Data Success");
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Update Data Failed");
			}
		}
	}
}
