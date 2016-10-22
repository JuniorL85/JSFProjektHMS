package com.jsf.hello.EJBs;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
				usr.setJournalId(rs.getInt("journalId"));

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
	public void add(Patient patient) {

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
					"root", "Sommar15");
			String myStat = "INSERT INTO patient(ssn,firstName,lastName,userName,password, doctorId, nurseId, testId, roomId, receptionistId) VALUES(?,?,?,?,?,?,?,?,?,?)";
			stat = con.prepareStatement(myStat);

			stat.setInt(1, patient.getSsn());
			stat.setString(2, patient.getFirstName());
			stat.setString(3, patient.getLastName());
			stat.setString(4, patient.getUserName());
			stat.setString(5, patient.getPassword());
			stat.setInt(6, patient.getDoctorId());
			stat.setInt(7, patient.getNurseId());
			stat.setInt(8, patient.getTestId());
			stat.setInt(9, patient.getRoomId());
			stat.setInt(10, patient.getReceptionistId());
			stat.setInt(11, patient.getJournalId());
			stat.executeUpdate();

			System.out.println("Info added successfully");

			con.close();
			stat.close();
		} catch (Exception e) {
			System.out.println(" SQLException :(");
			e.printStackTrace();
		}
	}
	public List<Patient> searchPat(String search)
	{
		list = new ArrayList<>();

		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
			String myStat = "SELECT patient.ssn, notes FROM patient INNER JOIN journal ON journal.ssn = patient.ssn WHERE patient.ssn LIKE '%"+search+"%'";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				Patient usr = new Patient();
				usr.setSsn(rs.getInt("ssn"));
				/*usr.setFirstName(rs.getString("firstName"));
				usr.setLastName(rs.getString("lastName"));
				usr.setUserName(rs.getString("userName"));
				usr.setPassword(rs.getString("password"));

				usr.setDoctorId(rs.getInt("doctorId"));
				usr.setNurseId(rs.getInt("nurseId"));
				usr.setTestId(rs.getInt("testId"));		
				usr.setRoomId(rs.getInt("roomId"));
				usr.setReceptionistId(rs.getInt("receptionistId"));
				usr.setJournalId(rs.getInt("journalId"));*/
				usr.setNotes(rs.getString("notes"));
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