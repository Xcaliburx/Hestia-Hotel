package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

public class CustomerRequest {

	private int customerRequestId;
	private int receptionistId;
	private int roomId;
	private String request;
	private Timestamp requestDateTime;
	private int handlerEmployeeId;
	private Timestamp responseDateTime;
	private String responseNote;

	public CustomerRequest(int receptionistId, int roomId, String request, Timestamp requestDateTime) {
		super();
		this.receptionistId = receptionistId;
		this.roomId = roomId;
		this.request = request;
		this.requestDateTime = requestDateTime;
	}
	
	public CustomerRequest(int customerRequestId, int handlerEmployeeId, Timestamp responseDateTime,
			String responseNote) {
		super();
		this.customerRequestId = customerRequestId;
		this.handlerEmployeeId = handlerEmployeeId;
		this.responseDateTime = responseDateTime;
		this.responseNote = responseNote;
	}

	public CustomerRequest() {
		
	}

	public int getCustomerRequestId() {
		return customerRequestId;
	}
	public void setCustomerRequestId(int customerRequestId) {
		this.customerRequestId = customerRequestId;
	}
	public int getReceptionistId() {
		return receptionistId;
	}
	public void setReceptionistId(int receptionistId) {
		this.receptionistId = receptionistId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public Timestamp getRequestDateTime() {
		return requestDateTime;
	}
	public void setRequestDateTime(Timestamp requestDateTime) {
		this.requestDateTime = requestDateTime;
	}
	public int getHandlerEmployeeId() {
		return handlerEmployeeId;
	}
	public void setHandlerEmployeeId(int handlerEmployeeId) {
		this.handlerEmployeeId = handlerEmployeeId;
	}
	public Timestamp getResponseDateTime() {
		return responseDateTime;
	}
	public void setResponseDateTime(Timestamp responseDateTime) {
		this.responseDateTime = responseDateTime;
	}
	public String getResponseNote() {
		return responseNote;
	}
	public void setResponseNote(String responseNote) {
		this.responseNote = responseNote;
	}
	
	public Vector<CustomerRequest> getAll(){
		Vector<CustomerRequest> tableData = new Vector<>();
		ResultSet rs = Session.getConnection().executeQuery("SELECT * FROM "
				+ "customerrequest WHERE handlerEmployeeId is null");
			try {
				while(rs.next()){
					CustomerRequest c = new CustomerRequest();
					c.setCustomerRequestId(rs.getInt("customerRequestId"));
					c.setReceptionistId(rs.getInt("receptionistId"));
					c.setRoomId(rs.getInt("roomId"));
					c.setRequest(rs.getString("request"));
					c.setRequestDateTime(rs.getTimestamp("requestDateTime"));
					tableData.add(c);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tableData;
	}
	
	public boolean addRequest(){
		PreparedStatement ps = Session.getConnection().prepareStatement("INSERT INTO customerrequest VALUES(default,?,?,?,?,?,?,?)");
		ResultSet rs = null;
		try {
			ps.setInt(1, receptionistId);	
			ps.setInt(2, roomId);
			ps.setString(3, request);
			ps.setTimestamp(4, requestDateTime);
			ps.setString(5, null);
			ps.setString(6, null);
			ps.setString(7, null);
			ps.execute();
			return true;
		} catch (SQLException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		}
		return false;
	}
	
	public boolean updateRequest(){
		PreparedStatement ps = Session.getConnection().
				prepareStatement("UPDATE customerrequest SET handlerEmployeeId = ?, responseDateTime = ?, responseNote = ? WHERE customerRequestId = ?");
		try {
			ps.setInt(1, handlerEmployeeId);
			ps.setTimestamp(2, responseDateTime);
			ps.setString(3, responseNote);
			ps.setInt(4, customerRequestId);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
