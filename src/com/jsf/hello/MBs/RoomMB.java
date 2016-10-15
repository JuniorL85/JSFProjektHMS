package com.jsf.hello.MBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="room")
@SessionScoped
public class RoomMB {

	private int roomId;
	private String roomType;
	private int roomStatus;
	private int receptionistId;
	
	
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

	public String getRoomValues(){
		System.out.println("Room Type =" + roomTypeOptions);
		return "submittedRoomInfo.xhtml";
	}
	
	public String add(){
		
		try {
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="INSERT INTO room(roomType,roomStatus) VALUES(?,?)";
	        stat = con.prepareStatement(myStat); 
	        
	        stat.setString(1, roomType);
	        stat.setInt(2, roomStatus);
	        //stat.setInt(3, receptionistId);


	        stat.executeUpdate();

	        System.out.println("Info added successfully");

	        con.close();
			stat.close();
	    } 
		catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }
    	return null;
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

	        System.out.println("user deleted successfully");
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
}
