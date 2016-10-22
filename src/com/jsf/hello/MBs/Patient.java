package com.jsf.hello.MBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.validation.constraints.*;

import com.jsf.hello.EJBs.DepartmentEJB;
import com.jsf.hello.EJBs.PatientEJB;


@ManagedBean(name = "patient")
@SessionScoped
public class Patient {

	
	PatientEJB patientEjb = new PatientEJB();
	
	String search;



	List<Patient> list;
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;

	int ssn;
	String firstName;
	String lastName;
	String userName;
	String password;
	
	int doctorId;
	int nurseId;
	int testId;
	int roomId;
	int receptionistId;
	int journalId;
	

	public void patientById(int ssn, String firstName, String lastName, String userName,
			String password, int doctorId, int nurseId, int testId, int roomId, int receptionistId, int journalId ) {
		this.ssn = ssn;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.doctorId = doctorId;
		this.nurseId = nurseId;
		this.testId = testId;
		this.roomId = roomId;
		this.receptionistId = receptionistId;
		this.journalId = journalId;
	}

	public int getJournalId() {
		return journalId;
	}

	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getNurseId() {
		return nurseId;
	}

	public void setNurseId(int nurseId) {
		this.nurseId = nurseId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getReceptionistId() {
		return receptionistId;
	}

	public void setReceptionistId(int receptionistId) {
		this.receptionistId = receptionistId;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Patient> searchPat(){
		return patientEjb.searchPat(search);
	}
	
	
	
	public String add() {

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
					"root", "Sommar15");
			String myStat = "INSERT INTO patient(ssn,firstName,lastName,userName,password, doctorId, nurseId, testId, roomId, receptionistId) VALUES(?,?,?,?,?,?,?,?,?,?)";
			stat = con.prepareStatement(myStat);

			stat.setInt(1, ssn);
			stat.setString(2, firstName);
			stat.setString(3, lastName);
			stat.setString(4, userName);
			stat.setString(5, password);
			stat.setInt(6, doctorId);
			stat.setInt(7, nurseId);
			stat.setInt(8, testId);
			stat.setInt(9, roomId);
			stat.setInt(10, receptionistId);
			stat.executeUpdate();

			System.out.println("Info added successfully");

			con.close();
			stat.close();
		} catch (Exception e) {
			System.out.println(" SQLException :(");
			e.printStackTrace();
		}
		return null;
	}


	
		
		
		
}



