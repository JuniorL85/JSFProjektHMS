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


@ManagedBean(name = "patient")
@SessionScoped
public class Patient {

	
	
	List<Patient> list;
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;

	int ssn;
	String firstName;
	String lastName;
	String userName;
	String password;

	public void patientById(int ssn, String firstName, String lastName, int departmentId, String userName,
			String password) {
		this.ssn = ssn;
		this.firstName = firstName;
		this.lastName = lastName;

		this.userName = userName;
		this.password = password;
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

	public String add() {

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false",
					"root", "Sommar15");
			String myStat = "INSERT INTO patient(ssn,firstName,lastName,userName,password) VALUES(?,?,?,?,?,?)";
			stat = con.prepareStatement(myStat);

			stat.setInt(1, ssn);
			stat.setString(2, firstName);
			stat.setString(3, lastName);

			stat.setString(4, userName);
			stat.setString(5, password);

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


	public void search(){
		try{
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
			String myStat = "Select * from patient where ssn like ('" + ssn +"')";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				Patient usr = new Patient();
				usr.setSsn(rs.getInt("ssn"));
			
				
			}
			con.close();
			stat.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
		}
	}



