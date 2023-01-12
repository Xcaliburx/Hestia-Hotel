package Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Vector;

import Model.Employee;
import Model.Journal;
import Model.Session;
import View.JournalView;

public class JournalController {

	public static JournalController controller = null;
	public Journal j;
	public static JournalController getInstance(){
        if (controller == null){
        	controller = new JournalController();
        }
            return controller;
    }
	
	public JournalController() {
		// TODO Auto-generated constructor stub
		j = new Journal();
	}
	
	public Vector<Journal> getAll(){
        return j.getAll();
    }
	
	public static void viewAllJOurnal(){
		new JournalView();
	}
	
	public static boolean addJournal(String message){
		Journal j = new Journal(Session.employee.getEmployeeId(), Date.valueOf(LocalDate.now()), message);
		return j.addJournal();
	}
}
