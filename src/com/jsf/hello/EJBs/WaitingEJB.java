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
				usr.setPatient_ssn(rs.getInt("patient_ssn"));
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
				usr.setPatient_ssn(rs.getInt("patient_ssn"));
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
}
