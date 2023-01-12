package Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Vector;

import Model.Attendance;
import Model.Session;
import View.AttendanceView;

public class AttendanceController {

	public static AttendanceController controller = null;
	public Attendance a;
	public static AttendanceController getInstance(){
        if (controller == null){
        	controller = new AttendanceController();
        }
            return controller;
    }
	
	public AttendanceController() {
		// TODO Auto-generated constructor stub
		a = new Attendance();
	}

	public Vector<Attendance> getAll(){
        return a.getAllbyId(Session.employee.getEmployeeId());
    }
	
	public static void viewAllAttendance(){
		new AttendanceView();
	}
	
	public static boolean addAttendance(String attendanceType){
		Attendance a = new Attendance(Session.employee.getEmployeeId(), Timestamp.valueOf(LocalDateTime.now()), attendanceType);
		return a.addAttendance();
	}
}
