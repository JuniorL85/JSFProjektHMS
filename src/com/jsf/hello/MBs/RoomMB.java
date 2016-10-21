package com.jsf.hello.MBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jsf.hello.EJBs.RoomEJB;

@ManagedBean(name="room")
@SessionScoped
public class RoomMB {
	
	RoomEJB roomEjb = new RoomEJB();

	private int roomId;
	private String roomType;
	private int roomStatus;
	private int receptionistId;
	private String search;
	
	
	List<String> roomTypeOptions;
	
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	
	public RoomMB(){
		//populate list of StaffRole
				roomTypeOptions = new ArrayList<>();
				
				roomTypeOptions.add("Private Room");
				roomTypeOptions.add("General Ward");
				roomTypeOptions.add("Examination Room");
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomNumber) {
		this.roomId = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public List<String> getRoomTypeOptions() {
		return roomTypeOptions;
	}

	public void setRoomTypeOptions(List<String> roomTypeOptions) {
		this.roomTypeOptions = roomTypeOptions;
	}
	public int getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(int roomStatus) {
		this.roomStatus = roomStatus;
	}

	public int getReceptionistId() {
		return receptionistId;
	}

	public void setReceptionistId(int receptionistId) {
		this.receptionistId = receptionistId;
	}
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void add(){
		
		try {
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="INSERT INTO room(roomType,roomStatus) VALUES(?,?)";
	        stat = con.prepareStatement(myStat); 
	        
	        stat.setString(1, roomType);
	        stat.setInt(2, roomStatus);


	        stat.executeUpdate();

	        System.out.println("Info added successfully");

	        con.close();
			stat.close();
	    } 
		catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }
    }
	
	public void delete(int roomId) {
		
		if (roomId !=0){
	    try {
	    	//Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="delete FROM room WHERE roomId=" + roomId;
	        stat = con.prepareStatement(myStat); 
	        int i = stat.executeUpdate();
	        if (i >0){

	        System.out.println("Room deleted successfully");
	        }
	        con.close();
			stat.close();


	    } catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }
	//list.remove(userBean);
	//return list;
	    }}
	
	public void update(int roomId) {
		
		if (roomId !=0){
	    try {
	    	//Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="UPDATE room set roomType=?, roomStatus =? WHERE roomId=" + roomId;
	        stat = con.prepareStatement(myStat);
	        
	        stat.setString(1, getRoomType());
	        stat.setInt(2, getRoomStatus());
	        int i = stat.executeUpdate();
	        if (i >0){

	        System.out.println("Room updated successfully");
	        }
	        con.close();
			stat.close();


	    } catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }

	    }}
	public void roomById(String roomType, int roomStatus){
		this.roomType = roomType;
		this.roomStatus = roomStatus;
	}
	
	public void updateRoom(){
		//RoomEJB room1 = new RoomEJB();
		//room1.getRoomList();
		update(roomId);
	}
	public List<RoomMB> searchRoom(){
		return roomEjb.searchRoom(search);
	}
}
