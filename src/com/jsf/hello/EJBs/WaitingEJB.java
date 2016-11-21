package com.jsf.hello.EJBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jsf.hello.MBs.WaitingMB;
@ManagedBean(name="waitBean")
@SessionScoped
public class WaitingEJB {

	
	List<WaitingMB> list;
	
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	
	public List<WaitingMB> getWaitList()
	{
		list = new ArrayList<>();

		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
			String myStat = "SELECT * FROM waiting";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				WaitingMB usr = new WaitingMB();
				usr.setWaitingId(rs.getInt("waitingId"));
				usr.setReceptionstId(rs.getInt("receptionistId"));
				usr.setPatient_ssn(rs.getLong("patient_ssn"));
				usr.setRooms_roomId(rs.getInt("rooms_roomId"));
				
				
				
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

	public List<WaitingMB> getPatientSsnList()
	{
		list = new ArrayList<>();

		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
			String myStat = "SELECT patient_ssn FROM waiting";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				WaitingMB usr = new WaitingMB();
				//usr.setWaitingId(rs.getInt("waitingId"));
				//usr.setReceptionstId(rs.getInt("receptionistId"));
				usr.setPatient_ssn(rs.getLong("patient_ssn"));
				//usr.setRooms_roomId(rs.getInt("rooms_roomId"));
				
				
				
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

	public List<WaitingMB> searchPatroom(String search)
	{
		list = new ArrayList<>();

		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
			String myStat = "SELECT patient_ssn, rooms_roomId, roomType, maxCapacity, capacityNow FROM waiting LEFT JOIN rooms ON roomId = waiting.rooms_roomId WHERE waiting.patient_ssn LIKE '%"+search+"%'";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				WaitingMB usr = new WaitingMB();
				usr.setPatient_ssn(rs.getLong("patient_ssn"));
				usr.setRooms_roomId(rs.getInt("rooms_roomId"));
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
public void update(WaitingMB waitingMB) {
		
	    try {
	    	//Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="UPDATE waiting SET rooms_roomId = ? WHERE patient_ssn = ?";
	        stat = con.prepareStatement(myStat);
	        
	        stat.setInt(1, waitingMB.getRooms_roomId());
	        stat.setLong(2, waitingMB.getPatient_ssn());
	        
	        stat.executeUpdate();

	        con.close();
			stat.close();


	    } catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }}

}
