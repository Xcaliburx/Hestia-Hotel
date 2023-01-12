package View;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.CustomerRequestController;
import Controller.RoomController;
import Model.Room;

public class InsertRequestView extends JFrame implements ActionListener{

	public InsertRequestView() {
		// TODO Auto-generated constructor stub
		initFrame();
		setComponent();
		
		setVisible(true);
	}
	
	JComboBox<String> roomComboBox;
	JLabel lblRequest;
	JTextField txtRequest;
	JPanel topPanel,midPanel,botPanel;
	JButton btnSubmit;

	private void setComponent() {
		// TODO Auto-generated method stub
		Vector<Room> roomList = RoomController.getInstance().getAll();
		List<String> data = roomList.stream()
				.map(room -> String.valueOf(room.getRoomId()))
				.collect(Collectors.toList());
		roomComboBox = new JComboBox<>(data.toArray(String[]::new));
		roomComboBox.setSelectedIndex(1);
		roomComboBox.addActionListener(this);
		
		lblRequest = new JLabel("Request : ");
	    txtRequest = new JTextField();

	    btnSubmit = new JButton("Insert");
		topPanel = new JPanel();
		midPanel = new JPanel();
		botPanel = new JPanel();
	    
		midPanel.setLayout(new GridLayout(9,2));
		midPanel.add(lblRequest);
		midPanel.add(txtRequest);
		midPanel.add(new JLabel("Room Id : "));
		midPanel.add(roomComboBox);
		
		btnSubmit.addActionListener(this);
		botPanel.add(btnSubmit);
		
		add(topPanel,BorderLayout.NORTH);
		add(midPanel,BorderLayout.CENTER);
		add(botPanel,BorderLayout.SOUTH);
	}

	public void initFrame(){
		setSize(560,260);
		setLocationRelativeTo(null);
		setTitle("Insert Request");
		setResizable(false);
		setLayout(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnSubmit){			
			if(txtRequest.getText().equals("")){
				JOptionPane.showMessageDialog(this, "Data cannot be empty");
			} else {
				if(CustomerRequestController.addRequest(Integer.parseInt(roomComboBox.getSelectedItem().toString()), txtRequest.getText())){
					JOptionPane.showMessageDialog(this, "Insert Data Success");
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Insert Data Failed");
				}
			}
		}
	}
}
