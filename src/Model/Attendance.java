package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import Controller.EmployeeController;

public class Attendance {

	private int employeeId;
	private Timestamp attendanceDateTime;
	private String attendanceType;
	
	public Attendance(int employeeId, Timestamp attendanceDateTime, String attendanceType) {
		super();
		this.employeeId = employeeId;
		this.attendanceDateTime = attendanceDateTime;
		this.attendanceType = attendanceType;
	}
	
	public Attendance() {

	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public Timestamp getAttendanceDateTime() {
		return attendanceDateTime;
	}
	public void setAttendanceDateTime(Timestamp attendanceDateTime) {
		this.attendanceDateTime = attendanceDateTime;
	}
	public String getAttendanceType() {
		return attendanceType;
	}
	public void setAttendanceType(String attendanceType) {
		this.attendanceType = attendanceType;
	}
	
	public Vector<Attendance> getAllbyId(int id){
		Vector<Attendance> tableData = new Vector<>();
		ResultSet rs = Session.getConnection().executeQuery("SELECT * FROM "
				+ "attendance" +" WHERE employeeId = " + id);
			try {
				while(rs.next()){
					Attendance a = new Attendance();		
					a.setEmployeeId(rs.getInt("employeeId"));
					a.setAttendanceDateTime(rs.getTimestamp("attendanceDateTime"));
					a.setAttendanceType(rs.getString("attendanceType"));
					tableData.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tableData;
	}
	
	public boolean addAttendance(){
		PreparedStatement ps = Session.getConnection().prepareStatement("INSERT INTO attendance VALUES(?,?,?)");
		ResultSet rs = null;
		try {
			ps.setInt(1, employeeId);	
			ps.setTimestamp(2, attendanceDateTime);
			ps.setString(3, attendanceType);
			ps.execute();
			return true;
		} catch (SQLException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		}
		return false;
	}
}
