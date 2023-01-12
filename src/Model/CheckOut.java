package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import Controller.EmployeeController;

public class CheckOut {

	private int checkOutId;
	private int checkInId;
	private Timestamp dateTime;
	private int receptionistId;
	private int paymentAmount;
	private String paymentType;
	
	public CheckOut(int checkInId, Timestamp dateTime, int receptionistId, int paymentAmount, String paymentType) {
		super();
		this.checkInId = checkInId;
		this.dateTime = dateTime;
		this.receptionistId = receptionistId;
		this.paymentAmount = paymentAmount;
		this.paymentType = paymentType;
	}
	
	public CheckOut() {

	}

	public int getCheckOutId() {
		return checkOutId;
	}
	public void setCheckOutId(int checkOutId) {
		this.checkOutId = checkOutId;
	}
	public int getCheckInId() {
		return checkInId;
	}
	public void setCheckInId(int checkInId) {
		this.checkInId = checkInId;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public int getReceptionistId() {
		return receptionistId;
	}
	public void setReceptionistId(int receptionistId) {
		this.receptionistId = receptionistId;
	}
	public int getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public Vector<CheckOut> getAll(){
		Vector<CheckOut> tableData = new Vector<>();
		ResultSet rs = Session.getConnection().executeQuery("SELECT * FROM "
				+ "checkout");
			try {
				while(rs.next()){
					CheckOut c = new CheckOut();
					c.setCheckOutId(rs.getInt("checkOutId"));
					c.setCheckInId(rs.getInt("checkInId"));
					c.setDateTime(rs.getTimestamp("dateTime"));
					c.setReceptionistId(rs.getInt("receptionistId"));
					c.setPaymentAmount(rs.getInt("paymentAmount"));
					c.setPaymentType(rs.getString("paymentType"));
					tableData.add(c);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tableData;
	}

	public boolean addCheckOut(){
		PreparedStatement ps = Session.getConnection().prepareStatement("INSERT INTO checkout VALUES(default,?,?,?,?,?)");
		ResultSet rs = null;
		try {
			ps.setInt(1, checkInId);	
			ps.setTimestamp(2, dateTime);
			ps.setInt(3, receptionistId);
			ps.setInt(4, paymentAmount);
			ps.setString(5, paymentType);
			ps.execute();
			return true;
		} catch (SQLException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		}
		return false;
	}
}
