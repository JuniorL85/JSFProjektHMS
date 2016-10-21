package com.jsf.hello.EJBs;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jsf.hello.MBs.HospitalStaffMB;
import com.jsf.hello.MBs.Patient;
@ManagedBean(name = "patBean")

public class PatientEJB {

	List<Patient> list;
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	
	
	
	public List<Patient> getPatList()
	{
		list = new ArrayList<>();

		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
			String myStat = "SELECT * FROM patient";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				Patient usr = new Patient();
				usr.setSsn(rs.getInt("ssn"));
				usr.setFirstName(rs.getString("firstName"));
				usr.setLastName(rs.getString("lastName"));
				usr.setUserName(rs.getString("userName"));
				usr.setPassword(rs.getString("password"));

				usr.setDoctorId(rs.getInt("doctorId"));
				usr.setNurseId(rs.getInt("nurseId"));
				usr.setTestId(rs.getInt("testId"));		
				usr.setRoomId(rs.getInt("roomId"));
				usr.setReceptionistId(rs.getInt("receptionistId"));

				list.add(usr);
			}
			con.close();
			stat.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
		}
		return list;
	}
	

	
	
}