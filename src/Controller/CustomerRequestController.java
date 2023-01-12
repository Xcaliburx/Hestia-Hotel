package Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Vector;

import Model.CustomerRequest;
import Model.Employee;
import Model.Session;
import View.CustomerRequestView;
import View.InsertEmployeeView;
import View.InsertRequestView;

public class CustomerRequestController {

	public static CustomerRequestController controller = null;
	public CustomerRequest e;
	public static CustomerRequestController getInstance(){
        if (controller == null){
        	controller = new CustomerRequestController();
        }
            return controller;
    }
	
	public CustomerRequestController() {
		// TODO Auto-generated constructor stub
		e = new CustomerRequest();
	}
	
	public Vector<CustomerRequest> getAll(){
        return e.getAll();
    }
	
	public static void viewInsertRequestForm(){
		new InsertRequestView();
	}
	
	public static void viewAllRequest(){
		new CustomerRequestView();
	}

	public static boolean addRequest(int roomId, String request){
		CustomerRequest c = new CustomerRequest(Session.employee.getEmployeeId(), roomId, request, Timestamp.valueOf(LocalDateTime.now()));
		return c.addRequest();
	}
	
	public static boolean updateRequest(String note, int id) {
		CustomerRequest c = new CustomerRequest(id, Session.employee.getEmployeeId(), Timestamp.valueOf(LocalDateTime.now()), note);
		return c.updateRequest();
	}
}
