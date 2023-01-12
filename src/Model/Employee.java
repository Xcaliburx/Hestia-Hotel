package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Controller.EmployeeController;

public class Employee {

	private int employeeId;
	private String employeeUsername;
	private String employeePassword;
	private String employeeEmail;
	private String employeeName;
	private String employeeGender;
	private String employeeBirthDate;
	private String employeeRole;
	private Boolean isFired;
	
	public Employee(int employeeId, String employeePassword, String employeeName, String employeeGender, String employeeBirthDate, String employeeRole,
			Boolean isFired) {
		super();
		this.employeeId = employeeId;
		this.employeePassword = employeePassword;
		this.employeeName = employeeName;
		this.employeeGender = employeeGender;
		this.employeeBirthDate = employeeBirthDate;
		this.employeeRole = employeeRole;
		this.isFired = isFired;
	}

	public Employee(String employeeUsername, String employeePassword, String employeeEmail,
			String employeeName, String employeeGender, String employeeBirthDate, String employeeRole,
			Boolean isFired) {
		super();
		this.employeeUsername = employeeUsername;
		this.employeePassword = employeePassword;
		this.employeeEmail = employeeEmail;
		this.employeeName = employeeName;
		this.employeeGender = employeeGender;
		this.employeeBirthDate = employeeBirthDate;
		this.employeeRole = employeeRole;
		this.isFired = isFired;
	}
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeUsername() {
		return employeeUsername;
	}
	public void setEmployeeUsername(String employeeUsername) {
		this.employeeUsername = employeeUsername;
	}
	public String getEmployeePassword() {
		return employeePassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeGender() {
		return employeeGender;
	}
	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}
	public String getEmployeeBirthDate() {
		return employeeBirthDate;
	}
	public void setEmployeeBirthDate(String employeeBirthDate) {
		this.employeeBirthDate = employeeBirthDate;
	}
	public String getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	public Boolean getIsFired() {
		return isFired;
	}
	public void setIsFired(Boolean isFired) {
		this.isFired = isFired;
	}
	
	public Vector<Employee> getAll(){
		Vector<Employee> tableData = new Vector<>();
		ResultSet rs = Session.getConnection().executeQuery("SELECT * FROM "
				+ "Employee");
			try {
				while(rs.next()){
					Employee e = new Employee();
					e.setEmployeeId(rs.getInt("employeeId"));
					e.setEmployeeUsername(rs.getString("employeeUsername"));
					e.setEmployeeEmail(rs.getString("employeeEmail"));
					e.setEmployeeName(rs.getString("employeeName"));
					e.setEmployeeGender(rs.getString("employeeGender"));
					e.setEmployeeBirthDate(rs.getString("EmployeeBirthDate"));	
					e.setEmployeeRole(rs.getString("EmployeeRole"));
					e.setIsFired(rs.getBoolean("isFired"));
					tableData.add(e);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tableData;
	}
	
	public boolean addEmployee(){
		Employee e = EmployeeController.getEmployee(employeeUsername);
		if(e != null){
			return false;
		}
		PreparedStatement ps = Session.getConnection().prepareStatement("INSERT INTO employee VALUES(default,?,?,?,?,?,?,?,?)");
		ResultSet rs = null;
		try {
			ps.setString(1, employeeUsername);	
			ps.setString(2, employeePassword);
			ps.setString(3, employeeEmail);
			ps.setString(4, employeeName);
			ps.setString(5, employeeGender);
			ps.setString(6, employeeBirthDate);
			ps.setString(7, employeeRole);
			ps.setBoolean(8, isFired);
			ps.execute();
			return true;
		} catch (SQLException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		}
		return false;
	}
	
	public static Employee getEmployee(String username){
		ResultSet rs = Session.getConnection().executeQuery("SELECT * FROM "
				+ "Employee" +" WHERE EmployeeUsername = '" + username+"'");
		Employee e = new Employee();
		try {
			while(rs.next()){
				e.setEmployeeId(rs.getInt("employeeId"));
				e.setEmployeeUsername(rs.getString("employeeUsername"));
				e.setEmployeePassword(rs.getString("employeePassword"));
				e.setEmployeeEmail(rs.getString("employeeEmail"));
				e.setEmployeeName(rs.getString("employeeName"));
				e.setEmployeeGender(rs.getString("employeeGender"));
				e.setEmployeeBirthDate(rs.getString("EmployeeBirthDate"));	
				e.setEmployeeRole(rs.getString("EmployeeRole"));	
				e.setIsFired(rs.getBoolean("isFired"));
				return e;
			}
		} catch (SQLException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		}
		return null;
	}
	
	public static Employee getEmployeebyId(int id){
		ResultSet rs = Session.getConnection().executeQuery("SELECT * FROM "
				+ "employee" +" WHERE employeeId = " + id);
		Employee e = new Employee();
		try {
			while(rs.next()){
				e.setEmployeeId(rs.getInt("employeeId"));
				e.setEmployeeUsername(rs.getString("employeeUsername"));
				e.setEmployeePassword(rs.getString("employeePassword"));
				e.setEmployeeEmail(rs.getString("employeeEmail"));
				e.setEmployeeName(rs.getString("employeeName"));
				e.setEmployeeGender(rs.getString("employeeGender"));
				e.setEmployeeBirthDate(rs.getString("EmployeeBirthDate"));	
				e.setEmployeeRole(rs.getString("EmployeeRole"));	
				e.setIsFired(rs.getBoolean("isFired"));
				return e;
			}
		} catch (SQLException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		}
		return null;
	}
	
	public boolean updateEmployee(){
		PreparedStatement ps = Session.getConnection().
				prepareStatement("UPDATE employee SET employeePassword = ?, employeeName = ?, employeeGender = ?, employeeBirthDate = ?, employeeRole = ?, isFired = ? WHERE employeeID = ?");
		try {
			ps.setString(1, employeePassword);
			ps.setString(2, employeeName);
			ps.setString(3, employeeGender);
			ps.setString(4, employeeBirthDate);
			ps.setString(5, employeeRole);
			ps.setBoolean(6, isFired);
			ps.setInt(7, employeeId);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
