package Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Vector;

import Model.CheckOut;
import Model.Session;
import View.CheckOutView;
import View.InsertCheckOutView;

public class CheckOutController {

	public static CheckOutController controller = null;
	public CheckOut e;
	public static CheckOutController getInstance(){
        if (controller == null){
        	controller = new CheckOutController();
        }
            return controller;
    }
	
	public CheckOutController() {
		// TODO Auto-generated constructor stub
		e = new CheckOut();
	}
	
	public Vector<CheckOut> getAll(){
        return e.getAll();
    }
	
	public static void viewAllCheckout(){
		new CheckOutView();
	}
	
	public static void viewInsertCheckoutForm(){
		new InsertCheckOutView();
	}

	public static boolean addCheckOut(int checkInId, String paymentType){
		int payment = 0;
		if (paymentType.equals("cash")) {
			payment = 50000;
		} else {
			payment = 75000;
		}
		CheckOut c = new CheckOut(checkInId, Timestamp.valueOf(LocalDateTime.now()), Session.employee.getEmployeeId(), payment, paymentType);
		return c.addCheckOut();
	}
}
