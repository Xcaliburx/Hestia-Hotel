package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Controller.EmployeeController;

public class Journal {

	private int journalId;
	private int securityStaffId;
	private Date journalDate;
	private String journalMessage;
	
	public Journal(int securityStaffId, Date journalDate, String journalMessage) {
		super();
		this.securityStaffId = securityStaffId;
		this.journalDate = journalDate;
		this.journalMessage = journalMessage;
	}

	public Journal() {

	}

	public int getJournalId() {
		return journalId;
	}
	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}
	public int getSecurityStaffId() {
		return securityStaffId;
	}
	public void setSecurityStaffId(int securityStaffId) {
		this.securityStaffId = securityStaffId;
	}
	public Date getJournalDate() {
		return journalDate;
	}
	public void setJournalDate(Date journalDate) {
		this.journalDate = journalDate;
	}
	public String getJournalMessage() {
		return journalMessage;
	}
	public void setJournalMessage(String journalMessage) {
		this.journalMessage = journalMessage;
	}
	
	public Vector<Journal> getAll(){
		Vector<Journal> tableData = new Vector<>();
		ResultSet rs = Session.getConnection().executeQuery("SELECT * FROM "
				+ "journal");
			try {
				while(rs.next()){
					Journal j = new Journal();
					j.setJournalId(rs.getInt("journalId"));
					j.setSecurityStaffId(rs.getInt("securityStaffId"));
					j.setJournalDate(rs.getDate("journalDate"));
					j.setJournalMessage(rs.getString("journalMessage"));
					tableData.add(j);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tableData;
	}
	
	public boolean addJournal(){
		PreparedStatement ps = Session.getConnection().prepareStatement("INSERT INTO journal VALUES(default,?,?,?)");
		ResultSet rs = null;
		try {
			ps.setInt(1, securityStaffId);	
			ps.setDate(2, journalDate);
			ps.setString(3, journalMessage);
			ps.execute();
			return true;
		} catch (SQLException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		}
		return false;
	}
}
