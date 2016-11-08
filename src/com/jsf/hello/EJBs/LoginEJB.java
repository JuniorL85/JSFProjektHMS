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

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginEJB {
	
	List<Patient> list;
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	
	public List<Patient> searchPat(int patientSsn)
	{
		list = new ArrayList<>();

		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
			String myStat = "SELECT patient.ssn, notes, tests, medicine FROM patient JOIN journal ON journal.patient_ssn = patient.ssn WHERE patient.ssn LIKE '%"+patientSsn+"%'";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				Patient usr = new Patient();
				usr.setSsn(rs.getInt("ssn"));
				usr.setNotes(rs.getString("notes"));
				usr.setTests(rs.getString("tests"));
				usr.setMedicine(rs.getString("medicine"));
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
