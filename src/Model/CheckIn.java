package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import Controller.EmployeeController;

public class CheckIn {

	private int checkInId;
	private Timestamp dateTime;
	private int receptionistId;
	private int numberOfCustomer;
	private int roomId;
	
	public CheckIn(Timestamp dateTime, int receptionistId, int numberOfCustomer, int roomId) {
		super();
		this.dateTime = dateTime;
		this.receptionistId = receptionistId;
		this.numberOfCustomer = numberOfCustomer;
		this.roomId = roomId;
	}

	public CheckIn() {

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
	public int getNumberOfCustomer() {
		return numberOfCustomer;
	}
	public void setNumberOfCustomer(int numberOfCustomer) {
		this.numberOfCustomer = numberOfCustomer;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	public Vector<CheckIn> getAll(){
		Vector<CheckIn> tableData = new Vector<>();
		ResultSet rs = Session.getConnection().executeQuery("SELECT * FROM "
				+ "checkin");
			try {
				while(rs.next()){
					CheckIn c = new CheckIn();
					c.setCheckInId(rs.getInt("checkInId"));
					c.setDateTime(rs.getTimestamp("dateTime"));
					c.setReceptionistId(rs.getInt("receptionistId"));
					c.setNumberOfCustomer(rs.getInt("numberOfCustomer"));
					c.setRoomId(rs.getInt("roomId"));
					tableData.add(c);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tableData;
	}
	
	public boolean addCheckIn(){
		PreparedStatement ps = Session.getConnection().prepareStatement("INSERT INTO checkin VALUES(default,?,?,?,?)");
		ResultSet rs = null;
		try {
			ps.setTimestamp(1, dateTime);	
			ps.setInt(2, receptionistId);
			ps.setInt(3, numberOfCustomer);
			ps.setInt(4, roomId);
			ps.execute();
			return true;
		} catch (SQLException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		}
		return false;
	}
}
