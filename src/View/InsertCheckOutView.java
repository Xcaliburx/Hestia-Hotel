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

import Controller.CheckInController;
import Controller.CheckOutController;
import Model.CheckIn;

public class InsertCheckOutView extends JFrame implements ActionListener{

	public InsertCheckOutView() {
		// TODO Auto-generated constructor stub
		initFrame();
		setComponent();
		
		setVisible(true);
	}
	
	JComboBox<String> checkInComboBox;
	JComboBox<String> paymentTypeComboBox;
	JPanel topPanel,midPanel,botPanel;
	JButton btnSubmit;

	private void setComponent() {
		// TODO Auto-generated method stub		
		Vector<CheckIn> checkInList = CheckInController.getInstance().getAll();
		List<String> data = checkInList.stream()
				.map(checkIn -> String.valueOf(checkIn.getCheckInId()))
				.collect(Collectors.toList());
		checkInComboBox = new JComboBox<>(data.toArray(String[]::new));
		checkInComboBox.setSelectedIndex(1);
		checkInComboBox.addActionListener(this);
		
		String[] paymentType = {"cash", "digital"};
		paymentTypeComboBox = new JComboBox<>(paymentType);
		paymentTypeComboBox.setSelectedIndex(1);
		paymentTypeComboBox.addActionListener(this);

	    btnSubmit = new JButton("Insert");
		topPanel = new JPanel();
		midPanel = new JPanel();
		botPanel = new JPanel();
	    
		midPanel.setLayout(new GridLayout(9,2));
		midPanel.add(new JLabel("Check In Id : "));
		midPanel.add(checkInComboBox);
		midPanel.add(new JLabel("Payment Type : "));
		midPanel.add(paymentTypeComboBox);
		
		btnSubmit.addActionListener(this);
		botPanel.add(btnSubmit);
		
		add(topPanel,BorderLayout.NORTH);
		add(midPanel,BorderLayout.CENTER);
		add(botPanel,BorderLayout.SOUTH);
	}

	public void initFrame(){
		setSize(560,260);
		setLocationRelativeTo(null);
		setTitle("Insert Check Out");
		setResizable(false);
		setLayout(new BorderLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnSubmit){			
			if(CheckOutController.addCheckOut(Integer.parseInt(checkInComboBox.getSelectedItem().toString()), paymentTypeComboBox.getSelectedItem().toString())){
				JOptionPane.showMessageDialog(this, "Insert Data Success");
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Insert Data Failed");
			}
		}
	}
}
