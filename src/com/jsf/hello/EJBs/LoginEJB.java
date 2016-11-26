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
import com.jsf.hello.Util.DBHelper;


public class LoginEJB {
	
	List<Patient> list;
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	
	public List<Patient> searchPat(long patientSsn)
	{
		list = new ArrayList<>();

		
		try{
			con = DBHelper.getDBConnection();
			String myStat = "SELECT patient.ssn, notes, tests, medicine, bill, testResult FROM patient JOIN journal ON journal.patient_ssn = patient.ssn WHERE patient.ssn LIKE '%"+patientSsn+"%'";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				Patient usr = new Patient();
				usr.setSsn(rs.getLong("ssn"));
				usr.setNotes(rs.getString("notes"));
				usr.setTests(rs.getString("tests"));
				usr.setMedicine(rs.getString("medicine"));
				usr.setBill(rs.getInt("bill"));
				usr.setTestResult(rs.getString("testResult"));
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
