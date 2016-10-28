package com.jsf.hello.EJBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jsf.hello.MBs.RoomMB;

@ManagedBean(name="roomBean")
@SessionScoped
public class RoomEJB {

	List<RoomMB> list;
	
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	
	public List<RoomMB> getRoomList()
	{
		list = new ArrayList<>();

		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
			String myStat = "SELECT * FROM room";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				RoomMB usr = new RoomMB();
				usr.setRoomId(rs.getInt("roomId"));
				usr.setRoomType(rs.getString("roomType"));
				usr.setRoomStatus(rs.getInt("roomStatus"));
				usr.setReceptionistId(rs.getInt("receptionistId"));
				usr.setPatient_ssn(rs.getInt("patient_ssn"));
				list.add(usr);
			}
			con.close();
			stat.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public void add(RoomMB roommb){
		
		try {
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="INSERT INTO room(roomType,roomStatus) VALUES(?,?)";
	        stat = con.prepareStatement(myStat); 

	        stat.setString(1, roommb.getRoomType());
	        stat.setInt(2, roommb.getRoomStatus());

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

	    }}
	public void update(RoomMB roommb) {
		
	    try {
	    	//Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="UPDATE room SET roomType = ?, roomStatus = ? WHERE roomId = ?";
	        stat = con.prepareStatement(myStat);
	        
	        stat.setString(1, roommb.getRoomType());
	        stat.setInt(2, roommb.getRoomStatus());
	        stat.setInt(3, roommb.getRoomId());
	        stat.executeUpdate();

	        con.close();
			stat.close();


	    } catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }}
	public List<RoomMB> searchRoom(String search)
	{
		list = new ArrayList<>();

		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
			String myStat = "SELECT * FROM room WHERE roomType LIKE '%"+search+"%' OR roomStatus LIKE '%"+search+"%'";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				RoomMB usr = new RoomMB();
				usr.setRoomId(rs.getInt("roomId"));
				usr.setRoomType(rs.getString("roomType"));
				usr.setRoomStatus(rs.getInt("roomStatus"));
				usr.setReceptionistId(rs.getInt("receptionistId"));
				list.add(usr);
			}
			con.close();
			stat.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
