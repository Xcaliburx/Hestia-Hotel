package Controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import Model.Employee;
import Model.Room;
import Model.Session;
import View.InsertRoomView;
import View.RoomView;
import View.UpdateRoomView;

public class RoomController {

	public static RoomController controller = null;
	public Room e;
	public static RoomController getInstance(){
        if (controller == null){
        	controller = new RoomController();
        }
            return controller;
    }
	
	public RoomController() {
		// TODO Auto-generated constructor stub
		e = new Room();
	}

	public Vector<Room> getAll(){
        return e.getAll();
    }
	
	public static void viewInsertRoomForm(){
		new InsertRoomView();
	}
	
	public static void viewAllRoom(){
		new RoomView();
	}
	
	public static void viewUpdateRoom(String id){
		new UpdateRoomView(id);
	}
	
	public static boolean updateRoom(String roomType, int roomPrice, int id){
		Room r = new Room(roomType, roomPrice, id);
		return r.updateRoom();
	}
	
	public static boolean deleteRoom(int id){
		System.out.println(id);
		PreparedStatement ps = Session.getConnection().
				prepareStatement("DELETE FROM room WHERE roomId = ?");
		try {
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean addRoom(int roomNumber, String roomType, int roomPrice){
		Room r = new Room(roomNumber, roomType, roomPrice);
		return r.addRoom();
	}
	
	public static Room getRoom(int roomNumber){
		return Room.getRoom(roomNumber);
	}
	
	public static Room getRoombyId(int id){
		return Room.getRoombyId(id);
	}
}
