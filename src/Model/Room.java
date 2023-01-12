package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Controller.EmployeeController;
import Controller.RoomController;

public class Room {

	private int roomId;
	private int roomNumber;
	private String roomType;
	private int roomPrice;
	
	public Room(int roomNumber, String roomType, int roomPrice) {
		super();
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
	}

	public Room(String roomType, int roomPrice, int roomId) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
	}

	public Room() {
		// TODO Auto-generated constructor stub
	}

	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}
	
	public Vector<Room> getAll(){
		Vector<Room> tableData = new Vector<>();
		ResultSet rs = Session.getConnection().executeQuery("SELECT * FROM "
				+ "room");
			try {
				while(rs.next()){
					Room r = new Room();
					r.setRoomId(rs.getInt("roomId"));
					r.setRoomNumber(rs.getInt("roomNumber"));
					r.setRoomType(rs.getString("roomType"));
					r.setRoomPrice(rs.getInt("roomPrice"));
					tableData.add(r);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tableData;
	}
	
	public static Room getRoom(int roomNumber){
		ResultSet rs = Session.getConnection().executeQuery("SELECT * FROM "
				+ "room" +" WHERE roomNumber = " + roomNumber);
		Room r = new Room();
		try {
			while(rs.next()){
				r.setRoomId(rs.getInt("roomId"));
				r.setRoomNumber(rs.getInt("roomNumber"));
				r.setRoomType(rs.getString("roomType"));
				r.setRoomPrice(rs.getInt("roomPrice"));
				return r;
			}
		} catch (SQLException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		}
		return null;
	}
	
	public static Room getRoombyId(int id){
		ResultSet rs = Session.getConnection().executeQuery("SELECT * FROM "
				+ "room" +" WHERE roomId = " + id);
		Room r = new Room();
		try {
			while(rs.next()){
				r.setRoomId(rs.getInt("roomId"));
				r.setRoomNumber(rs.getInt("roomNumber"));
				r.setRoomType(rs.getString("roomType"));
				r.setRoomPrice(rs.getInt("roomPrice"));
				return r;
			}
		} catch (SQLException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		}
		return null;
	}
	
	public boolean addRoom(){
		Room r = RoomController.getRoom(roomNumber);
		if(r != null){
			return false;
		}
		PreparedStatement ps = Session.getConnection().prepareStatement("INSERT INTO room VALUES(default,?,?,?)");
		ResultSet rs = null;
		try {
			ps.setInt(1, roomNumber);	
			ps.setString(2, roomType);
			ps.setInt(3, roomPrice);
			ps.execute();
			return true;
		} catch (SQLException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		}
		return false;
	}
	
	public boolean updateRoom(){
		PreparedStatement ps = Session.getConnection().
				prepareStatement("UPDATE room SET roomType = ?, roomPrice = ? WHERE roomId = ?");
		try {
			ps.setString(1, roomType);
			ps.setInt(2, roomPrice);
			ps.setInt(3, roomId);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
