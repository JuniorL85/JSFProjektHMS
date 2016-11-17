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
	
	public void add(HospitalStaffMB hospitalstaffmb){
		
		try {
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="INSERT INTO employee(jobTitle,firstName,lastName,departmentId,userName,password) VALUES(?,?,?,?,?,?)";
	        stat = con.prepareStatement(myStat); 
	        
	        stat.setString(1, hospitalstaffmb.getJobTitle());
	        stat.setString(2, hospitalstaffmb.getFirstName());
	        stat.setString(3, hospitalstaffmb.getLastName());
	        stat.setInt(4, hospitalstaffmb.getDepartmentId());
	        stat.setString(5, hospitalstaffmb.getUserName());
	        stat.setString(6, hospitalstaffmb.getPassword());

	        stat.executeUpdate();

	        System.out.println("Info added successfully");

	        con.close();
			stat.close();
	    } 
		catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }
    }
	
	public void delete(int employeeId) {
		
		if (employeeId !=0){
	    try {
	    	//Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="delete FROM employee WHERE employeeId=" + employeeId;
	        stat = con.prepareStatement(myStat); 
	        int i = stat.executeUpdate();
	        if (i >0){

	        System.out.println("user deleted successfully");
	        }
	        con.close();
			stat.close();


	    } catch (Exception e) {
	        System.out.println(" SQLException :(");
	        e.printStackTrace();
	    }

	    }}
	
	public void update(HospitalStaffMB hospitalmb) {
		
	    try {
	    	//Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
	        String myStat ="UPDATE employee SET jobTitle = ?, firstName = ?, lastName= ?, departmentId= ?, userName= ?, password= ? WHERE employeeId = ?";
	        stat = con.prepareStatement(myStat); 
	        stat.setString(1, hospitalmb.getJobTitle());
	        stat.setString(2, hospitalmb.getFirstName());
	        stat.setString(3, hospitalmb.getLastName());
	        stat.setInt(4, hospitalmb.getDepartmentId());
	        stat.setString(5, hospitalmb.getUserName());
	        stat.setString(6, hospitalmb.getPassword());
	        stat.setInt(7, hospitalmb.getEmployeeId());
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
