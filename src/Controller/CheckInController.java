package Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Vector;

import Model.CheckIn;
import Model.Session;
import View.CheckInView;
import View.InsertCheckInView;
import View.InsertEmployeeView;

public class CheckInController {

	public static CheckInController controller = null;
	public CheckIn e;
	public static CheckInController getInstance(){
        if (controller == null){
        	controller = new CheckInController();
        }
            return controller;
    }
	
	public CheckInController() {
		// TODO Auto-generated constructor stub
		e = new CheckIn();
	}
	
	public static void viewInsertCheckInForm(){
		new InsertCheckInView();
	}

	public Vector<CheckIn> getAll(){
        return e.getAll();
    }
	
	public static void viewAllCheckIn(){
		new CheckInView();
	}
	
	public static boolean addCheckIn(int customerNumber, int roomId){
		CheckIn c = new CheckIn(Timestamp.valueOf(LocalDateTime.now()), Session.employee.getEmployeeId(), customerNumber, roomId);
		return c.addCheckIn();
	}
}
