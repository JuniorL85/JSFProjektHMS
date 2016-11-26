package com.jsf.hello.EJBs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jsf.hello.MBs.RoomMB;
import com.jsf.hello.Util.DBHelper;

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
			con = DBHelper.getDBConnection();
			String myStat = "SELECT * FROM rooms";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				RoomMB usr = new RoomMB();
				usr.setRoomId(rs.getInt("roomId"));
				usr.setRoomType(rs.getString("roomType"));
				usr.setMaxCapacity(rs.getInt("maxCapacity"));
				usr.setCapacityNow(rs.getInt("capacityNow"));
				
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
			con = DBHelper.getDBConnection();
	        String myStat ="INSERT INTO rooms(roomType,maxCapacity) VALUES(?,?)";
	        stat = con.prepareStatement(myStat); 

	        stat.setString(1, roommb.getRoomType());
	        stat.setInt(2, roommb.getMaxCapacity());

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
	    	con = DBHelper.getDBConnection();
	        String myStat ="delete FROM rooms WHERE roomId=" + roomId;
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
	    	con = DBHelper.getDBConnection();
	        String myStat ="UPDATE rooms SET roomType = ?, maxCapacity = ? WHERE roomId = ?";
	        stat = con.prepareStatement(myStat);
	        
	        stat.setString(1, roommb.getRoomType());
	        stat.setInt(2, roommb.getMaxCapacity());
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
			con = DBHelper.getDBConnection();
			String myStat = "SELECT * FROM rooms WHERE roomType LIKE '%"+search+"%' OR roomId LIKE '%"+search+"%'";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				RoomMB usr = new RoomMB();
				usr.setRoomId(rs.getInt("roomId"));
				usr.setRoomType(rs.getString("roomType"));
				usr.setMaxCapacity(rs.getInt("maxCapacity"));
				usr.setCapacityNow(rs.getInt("capacityNow"));
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
