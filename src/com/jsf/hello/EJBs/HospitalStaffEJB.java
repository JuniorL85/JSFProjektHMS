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

@ManagedBean(name="userBean")
@SessionScoped
public class HospitalStaffEJB {

	List<HospitalStaffMB> list;
	
	Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
	
	public List<HospitalStaffMB> getUserList()
	{
		list = new ArrayList<>();

		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
			String myStat = "SELECT * FROM employee";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				HospitalStaffMB usr = new HospitalStaffMB();
				usr.setEmployeeId(rs.getInt("employeeId"));
				usr.setJobTitle(rs.getString("jobTitle"));
				usr.setFirstName(rs.getString("firstName"));
				usr.setLastName(rs.getString("lastName"));
				usr.setDepartmentId(rs.getInt("departmentId"));
				usr.setUserName(rs.getString("userName"));
				usr.setPassword(rs.getString("password"));
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
	public void update(HospitalStaffMB hospitalmb) {
		
	    try {
	    	//Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="UPDATE employee SET jobTitle = ?, firstName = ?, lastName= ?, departmentId= ?, userName= ?, password= ? WHERE employeeId = ?";
	        stat = con.prepareStatement(myStat);
	        
	        stat.setInt(1, hospitalmb.getEmployeeId());
	        stat.setString(2, hospitalmb.getJobTitle());
	        stat.setString(3, hospitalmb.getFirstName());
	        stat.setString(4, hospitalmb.getLastName());
	        stat.setInt(5, hospitalmb.getDepartmentId());
	        stat.setString(6, hospitalmb.getUserName());
	        stat.setString(7, hospitalmb.getPassword());
	        stat.executeUpdate();
	        
	        con.close();
			stat.close();


	    } catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }

	    }
	public List<HospitalStaffMB> searchUser(String search)
	{
		list = new ArrayList<>();

		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
			String myStat = "SELECT * FROM employee WHERE firstName LIKE '%"+search+"%' OR lastName LIKE '%"+search+"%' OR jobTitle LIKE '%"+search+"%'";
			stat = con.prepareStatement(myStat);
			rs = stat.executeQuery();
			while(rs.next()){
				
				HospitalStaffMB usr = new HospitalStaffMB();
				usr.setEmployeeId(rs.getInt("employeeId"));
				usr.setJobTitle(rs.getString("jobTitle"));
				usr.setFirstName(rs.getString("firstName"));
				usr.setLastName(rs.getString("lastName"));
				usr.setDepartmentId(rs.getInt("departmentId"));
				usr.setUserName(rs.getString("userName"));
				usr.setPassword(rs.getString("password"));
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
