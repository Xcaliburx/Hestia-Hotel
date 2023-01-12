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

import Controller.RoomController;

public class InsertRoomView extends JFrame implements ActionListener{

	public InsertRoomView() {
		// TODO Auto-generated constructor stub
		initFrame();
		setComponent();
		
		setVisible(true);
	}
	
	JComboBox<String> typeComboBox;
	JLabel lblRoomNumber;
	JTextField txtRoomNumber;
	JPanel topPanel,midPanel,botPanel;
	JButton btnSubmit;

	private void setComponent() {
		// TODO Auto-generated method stub
		String[] data = {"standard", "premium"};
		typeComboBox = new JComboBox<>(data);
		typeComboBox.setSelectedIndex(1);
		typeComboBox.addActionListener(this);
		
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
	    txtRoomNumber = new JFormattedTextField(formatter);
		lblRoomNumber = new JLabel("Room Number : ");

	    btnSubmit = new JButton("Insert");
		topPanel = new JPanel();
		midPanel = new JPanel();
		botPanel = new JPanel();
	    
		midPanel.setLayout(new GridLayout(9,2));
		midPanel.add(lblRoomNumber);
		midPanel.add(txtRoomNumber);
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
		setTitle("Insert Room");
		setResizable(false);
		setLayout(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnSubmit){			
			if(txtRoomNumber.getText().equals("")){
				JOptionPane.showMessageDialog(this, "Data cannot be empty");
			} else if (Integer.parseInt(txtRoomNumber.getText()) < 1 || Integer.parseInt(txtRoomNumber.getText()) > 500) {
				JOptionPane.showMessageDialog(this, "Room number must be an integer between 1 and 500");
			} else {
				int price = 0;
				if (typeComboBox.getSelectedItem().toString().equals("standard")) {
					price = 8000;
				} else {
					price = 10000;
				}
				if(RoomController.addRoom(Integer.parseInt(txtRoomNumber.getText()), typeComboBox.getSelectedItem().toString(), price)){
					JOptionPane.showMessageDialog(this, "Insert Data Success");
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Insert Data Failed");
				}
			}
		}
	}
}
