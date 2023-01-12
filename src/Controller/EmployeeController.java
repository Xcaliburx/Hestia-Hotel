package Controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import Model.Employee;
import Model.Session;
import View.EmployeeView;
import View.InsertEmployeeView;
import View.UpdateEmployeeView;

public class EmployeeController {

	public static EmployeeController controller = null;
	public Employee e;
	public static EmployeeController getInstance(){
        if (controller == null){
        	controller = new EmployeeController();
        }
            return controller;
    }
	
	public EmployeeController() {
		// TODO Auto-generated constructor stub
		e = new Employee();
	}
	
	public Vector<Employee> getAll(){
        return e.getAll();
    }
	
	public static void viewInsertEmployeeForm(){
		new InsertEmployeeView();
	}
	
	public static void viewAllEmployee(){
		new EmployeeView();
	}
	
	public static void viewUpdateEmployee(String id){
		new UpdateEmployeeView(id);
	}
	
	public static boolean updateEmployee(String password, String name, String gender, String birthDate, String role, int id){
		Employee e = new Employee(id, password, name, gender, birthDate, role, false);
		return e.updateEmployee();
	}
	
	public static boolean fireEmployee(int id){
		PreparedStatement ps = Session.getConnection().
				prepareStatement("UPDATE employee SET isFired = ? WHERE employeeID = ?");
		try {
			ps.setBoolean(1, true);
			ps.setInt(2, id);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean addEmployee(String username, String password, String email, String name, String gender, String birthDate, String role){
		Employee e = new Employee(username, password, email, name, gender, birthDate, role, false);
		return e.addEmployee();
	}
	
	public static Employee getEmployee(String username){
		return Employee.getEmployee(username);
	}
	
	public static Employee getEmployeebyId(int id){
		return Employee.getEmployeebyId(id);
	}
}
