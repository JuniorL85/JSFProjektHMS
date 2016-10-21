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
